package fr.better.tools.command;

import fr.better.tools.BetterPlugin;
import fr.better.tools.command.action.MachineAction;
import fr.better.tools.command.action.PlayerAction;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SimpleCommand extends BetterCommand {

    private fr.better.tools.command.Parameter param;

    public SimpleCommand(Parameter parameter, BetterPlugin plugin) {
        plugin.getCommand(parameter.parameter()).setExecutor(this);
        param = parameter;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {

        List<String> args = Arrays.asList(strings);

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
    }
}
