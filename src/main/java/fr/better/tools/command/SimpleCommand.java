package fr.better.tools.command;

import fr.better.tools.BetterPlugin;
import fr.better.tools.exception.CommandNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SimpleCommand extends BetterCommand {

    private Parameter param;

    public SimpleCommand(String argument, BetterPlugin plugin) {
        try{
            plugin.getCommand(argument).setExecutor(this);
        }catch(NullPointerException e){
            try {
                throw new CommandNotFoundException(e.getCause());
            } catch (CommandNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void setParam(Parameter param) {
        this.param = param;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> args = Arrays.asList(strings);
        if (param instanceof PlayerParameter) {
            if (commandSender instanceof Player) {

                Player p = (Player) commandSender;
                PlayerParameter parameter = (PlayerParameter) param;
                String permission = parameter.permission();

                if(permission == null || permission.isEmpty() || p.hasPermission(permission)){
                    parameter.action(p, args);
                }else{
                    p.sendMessage(noPermission);
                }
            } else {
                commandSender.sendMessage(error);
            }

        } else if (param instanceof MachineParameter) {

            if (!(commandSender instanceof Player)) {
                ((MachineParameter) param).action(args);
            } else {
                commandSender.sendMessage(error);
            }

        } else if (param instanceof MixParameter) {

            if (commandSender instanceof Player) {

                Player p = (Player) commandSender;
                PlayerParameter parameter = (PlayerParameter) param;
                String permission = parameter.permission();

                if(permission == null || permission.isEmpty() || p.hasPermission(permission)){
                    parameter.action(p, args);
                }else{
                    p.sendMessage(noPermission);
                }
            } else {
                ((MachineParameter) param).action(args);
            }
        }
        return true;
    }
}
