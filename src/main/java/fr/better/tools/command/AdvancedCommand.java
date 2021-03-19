package fr.better.tools.command;

import fr.better.tools.BetterPlugin;
import fr.better.tools.command.abstraction.MachineParameter;
import fr.better.tools.command.abstraction.MixParameter;
import fr.better.tools.command.abstraction.Parameter;
import fr.better.tools.command.abstraction.PlayerParameter;
import fr.better.tools.system.Instantiaters;
import fr.better.tools.exception.CommandNotFoundException;
import fr.better.tools.utils.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class AdvancedCommand extends BetterCommand implements TabCompleter {

    private final Map<String, Parameter> all;
    private final String commandName;

    public AdvancedCommand(String commandName, BetterPlugin main){
        this.commandName = commandName;
        this.all = new HashMap<>();
        try{
            main.getCommand(commandName).setExecutor(this);
            main.getCommand(commandName).setTabCompleter(this);
        }catch(NullPointerException e){
            try {
                throw new CommandNotFoundException(e.getCause());
            } catch (CommandNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(strings.length > 0 && !strings[0].equalsIgnoreCase("help")) {

            Parameter param = null;

            for (Map.Entry<String, Parameter> entry : all.entrySet()) {

                Parameter p = entry.getValue();
                String a = entry.getKey();

                if (!a.equalsIgnoreCase(strings[0])) continue;
                param = p;
            }

            if (param == null) {
                commandSender.sendMessage(BetterCommand.getErrorArgument());
                return false;
            }

            List<String> args = new ArrayList<>();

            for (String part : strings) {
                if(part.equalsIgnoreCase(strings[0]))continue;
                args.add(part);
            }

            if(args.size() < getParameterSize(param, false)){
                commandSender.sendMessage(BetterCommand.getErrorParameter()
                        .replace("!cmd!", s)
                        .replace("!param!", param.parameter())
                );
                return false;
            }

            if(param instanceof PlayerParameter){

                if(commandSender instanceof Player){

                    PlayerParameter parameter = (PlayerParameter) param;
                    Player player = (Player) commandSender;
                    String permission = parameter.permission();

                    if(permission == null || permission.isEmpty() || player.hasPermission(permission)){
                        parameter.action(player, args);
                    }

                }else{
                    commandSender.sendMessage("You must be a player to do that");
                }

            }else if(param instanceof MixParameter){

                MixParameter parameter = (MixParameter) param;

                if(commandSender instanceof Player){

                    Player player = (Player) commandSender;
                    String permission = parameter.permission();

                    if(permission == null || permission.isEmpty() || player.hasPermission(permission)){
                        parameter.action(player, args);
                    }

                }else{
                    parameter.action(args);
                }

            }else if(param instanceof MachineParameter){

                MachineParameter parameter = (MachineParameter) param;

                if(!(commandSender instanceof Player)){
                    parameter.action(args);
                }else{
                    commandSender.sendMessage("§7Hey ! You must be a console Sender to do that !");
                }
            }
        }else{
            sendHelp(commandSender);
        }
        return false;
    }

    private void sendHelp(CommandSender commandSender){

        String cmd = Utility.firstToUpper(commandName);
        if(cmd.length() < 4)cmd = cmd.toUpperCase();

        if(commandSender instanceof Player){
            commandSender.sendMessage(BetterCommand.getMainColor() + "Command : " + cmd);
            commandSender.sendMessage("§8§m-----------------------");
            for(Map.Entry<String, Parameter> entry : all.entrySet()){
                Parameter param = entry.getValue();
                String perm;
                if(param instanceof PlayerParameter){
                    perm = ((PlayerParameter)param).permission();
                }else{
                    perm  = ((MixParameter)param).permission();
                }
                if(commandSender instanceof Player && !commandSender.hasPermission(perm))continue;
                commandSender.sendMessage(BetterCommand.getSecondColor()
                        + " • /" + commandName + " " + BetterCommand.getMainColor() + entry.getKey()
                        + " " + param.parameter() + " " + BetterCommand.getSecondColor()  + param.utility());
            }
            commandSender.sendMessage("§8§m-----------------------");
            String who = Instantiaters.getPlugin().getDescription().getAuthors().get(0);
            if(who != null && !who.isEmpty())
                commandSender.sendMessage(BetterCommand.getMainColor() + "By " + BetterCommand.getWho());
        }else{
            System.out.println("Flemme mdr !");
        }
    }

    private int getParameterSize(Parameter param, boolean optional){
        String[] split = param.parameter().split(" ");
        int size =0;
        for(String comp : split){
            if(comp.startsWith("<")) {
                size++;
            }else if(optional){
                size++;
            }
        }
        return size;
    }

    public void register(String s, Parameter parameter){
        all.put(s, parameter);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length != 0)return new ArrayList<>();
        List<String> result = new ArrayList<>();
        for(String param : all.keySet()){
            result.add(param);
        }
        result.add("help");
        return result;
    }
}
