package fr.better.tools.command;


import fr.better.tools.BetterPlugin;
import fr.better.tools.deprecated.Instantiaters;
import fr.better.tools.exception.CommandNotFoundException;
import fr.better.tools.utils.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvancedCommand extends BetterCommand {

    private final Map<String, Parameter> all;
    private final String commandName;

    public AdvancedCommand(String commandName, BetterPlugin main){
        this.commandName = commandName;
        this.all = new HashMap<>();
        try{
            main.getCommand(commandName).setExecutor(this);
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

            for (int i = 1; i <= param.parameterSize(); i++) {
                try {
                    args.add(strings[i]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
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
                    commandSender.sendMessage("§7Hey ! You must not be a player to do that !");
                }
            }
        }else{
            sendHelp(commandSender);
        }
        return false;
    }

    private void sendHelp(CommandSender commandSender){

        commandSender.sendMessage(BetterCommand.getTopHelpMessage().replace("!cmd!", Utility.firstToUpper(commandName)));
        commandSender.sendMessage("§8§m-----------------------");
        for(Map.Entry<String, Parameter> entry : all.entrySet()){
            Parameter param = entry.getValue();
            commandSender.sendMessage("§8» §7/" + commandName + " §3" + entry.getKey() + " " + param.parameter() + " §7" + param.utility());
        }
        commandSender.sendMessage("§8§m-----------------------");
        String who = Instantiaters.getPlugin().getDescription().getAuthors().get(0);
        if(who != null && !who.isEmpty())
        commandSender.sendMessage(BetterCommand.getFootHeadMessage() + who);
    }

    public void register(String s, Parameter parameter){
        all.put(s, parameter);
    }
}
