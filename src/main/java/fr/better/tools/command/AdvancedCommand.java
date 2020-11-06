package fr.better.tools.command;


import fr.better.tools.BetterPlugin;
import fr.better.tools.deprecated.Instantiaters;
import fr.better.tools.exception.CommandNotFoundException;
import fr.better.tools.command.action.MachineAction;
import fr.better.tools.command.action.PlayerAction;
import fr.better.tools.utils.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AdvancedCommand extends BetterCommand {

    private final List<Parameter> all;
    private final String commandName;

    public AdvancedCommand(String commandName, BetterPlugin main){

        this.commandName = commandName;
        this.all = new ArrayList<>();
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

        if(strings.length > 0 && !strings[0].equalsIgnoreCase("help")){

            Parameter param = null;

            for(fr.better.tools.command.Parameter p : all) {

                if (!p.argument().equalsIgnoreCase(strings[0]))continue;
                param = p;
            }

            if(param == null) {

                commandSender.sendMessage(noArgs);
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

            if(commandSender instanceof Player){

                Player p = (Player) commandSender;

                if(param.action() instanceof PlayerAction){

                    PlayerAction action = (PlayerAction) param.action();
                    String perm = action.requirePermission();

                    if(perm == null || p.hasPermission(perm) || perm.equalsIgnoreCase("no")){
                        action.executePlayer(p, args);
                    }else{
                        p.sendMessage(noPermission);
                    }

                }else{
                    p.sendMessage(noPermission);
                }
            } else {

                if(param.action() instanceof MachineAction){

                    ((MachineAction)param.action()).executeMachine(args);

                }else{
                    commandSender.sendMessage(error);
                }
            }

            return true;

        }else{
            sendHelp(commandSender);
        }
        return false;
    }

    private void sendHelp(CommandSender commandSender){

        commandSender.sendMessage("§8» §3" + Utility.firstToUpper(commandName) + " Command §8«");
        commandSender.sendMessage("§8§m-----------------------");
        for(Parameter param : all){
            commandSender.sendMessage("§8» §7/" + commandName + " §3" + param.argument() + " " + param.parameter() + " §7" + param.utility());
        }
        commandSender.sendMessage("§8§m-----------------------");
        String who = Instantiaters.getPlugin().whoAreYou();
        if(who != null && !who.isEmpty())
        commandSender.sendMessage("§7Developped by " + who);
    }

    public void register(Parameter parameter){
        all.add(parameter);
    }

}
