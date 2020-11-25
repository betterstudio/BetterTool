package fr.better.tools.command;

import fr.better.tools.BetterPlugin;
import fr.better.tools.config.BetterConfig;
import fr.better.tools.config.MessageManager;
import fr.better.tools.exception.CommandNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SimpleCommand extends BetterCommand {

    private Parameter param;
    private MessageManager manager;

    public SimpleCommand(String argument, BetterPlugin plugin) {
        try{
            plugin.getCommand(argument).setExecutor(this);
            manager = new MessageManager((BetterConfig) plugin.getBetterConfig());
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
                    p.sendMessage(manager.getErrorMessageNoPermission());
                }
            } else {
                commandSender.sendMessage("You must be a player to do that !");
            }

        } else if (param instanceof MachineParameter) {

            if (!(commandSender instanceof Player)) {
                ((MachineParameter) param).action(args);
            } else {
                commandSender.sendMessage("§7Hey ! You must don't be a player to do that !");
            }

        } else if (param instanceof MixParameter) {

            if (commandSender instanceof Player) {

                Player p = (Player) commandSender;
                PlayerParameter parameter = (PlayerParameter) param;
                String permission = parameter.permission();

                if(permission == null || permission.isEmpty() || p.hasPermission(permission)){
                    parameter.action(p, args);
                }else{
                    p.sendMessage(manager.getErrorMessageNoPermission());
                }
            } else {
                ((MachineParameter) param).action(args);
            }
        }
        return true;
    }
}
