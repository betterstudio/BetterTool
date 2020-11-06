package fr.better.tools.command;

import fr.better.tools.BetterPlugin;
import fr.better.tools.command.action.MachineAction;
import fr.better.tools.command.action.PlayerAction;
import fr.better.tools.exception.CommandNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SimpleCommand extends BetterCommand {

    private Parameter param;

    public SimpleCommand(Parameter parameter, BetterPlugin plugin) {
        try{
            plugin.getCommand(parameter.argument()).setExecutor(this);
        }catch(NullPointerException e){
            try {
                throw new CommandNotFoundException(e.getCause());
            } catch (CommandNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        param = parameter;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> args = Arrays.asList(strings);

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
    }
}
