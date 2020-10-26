package fr.better.tools.command;


import fr.better.tools.BetterPlugin;
import fr.better.tools.exception.CommandNotFoundException;
import fr.better.tools.command.action.MachineAction;
import fr.better.tools.command.action.PlayerAction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AdvancedCommand extends BetterCommand {

    private final List<fr.better.tools.command.Parameter> all;
    private final String commandName;

    public AdvancedCommand(List<fr.better.tools.command.Parameter> all, String commandName) {
        this.all = all;
        this.commandName = commandName;
    }

    public AdvancedCommand(String commandName, BetterPlugin main) throws CommandNotFoundException {
        this.commandName = commandName;
        this.all = new ArrayList<>();
        try{
            main.getCommand(commandName).setExecutor(this);
        }catch(Exception e){
            throw new CommandNotFoundException(e.getCause());
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


        if(strings.length > 0 && !strings[0].equalsIgnoreCase("help")){

            fr.better.tools.command.Parameter param = null;

            for(fr.better.tools.command.Parameter p : all) {

                if (!p.getArguments().equalsIgnoreCase(strings[0]))continue;
                param = p;

            }

            if(param == null) {
                commandSender.sendMessage(noArgs);
                return false;
            }

            List<String> args = new ArrayList<>();
            for (int i = 1; i <= param.getParameterSize(); i++) {
                try {
                    args.add(strings[i]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }

            if(commandSender instanceof Player){

                Player p = (Player) commandSender;

                if(param.getAction() instanceof PlayerAction){

                    PlayerAction action = (PlayerAction) param.getAction();
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

                if(param.getAction() instanceof MachineAction){

                    ((MachineAction)param.getAction()).executeMachine(args);

                }else{
                    commandSender.sendMessage(error);
                }
            }

            return true;

        }else{
            int i = 1;
            try{
                i = Integer.parseInt(strings[1]);
            }catch(ArrayIndexOutOfBoundsException ignored){}
            sendHelp(commandSender, i);
        }
        return false;
    }

    private void sendHelp(CommandSender commandSender, int page){

        int max = 10*page;
        int min = 10*(page-1);
        commandSender.sendMessage("§3" + commandName + "command");
        commandSender.sendMessage("§8§m-----------------------");
        for(fr.better.tools.command.Parameter param : all){
            int value = all.indexOf(param);
            if(min > value && max < value) continue;
            commandSender.sendMessage("§8» §7/" + commandName + " §3" + param.parameter() + " §7" + param.utility());
        }
        commandSender.sendMessage("§8§m-----------------------");
        commandSender.sendMessage("§7Please do /" + commandName + " help " + (page + 1) + " for more help !");
    }

    public void register(Parameter parameter){
        all.add(parameter);
    }

}
