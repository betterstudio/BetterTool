package fr.better.tools.command.base;

import fr.better.tools.command.content.Action;
import fr.better.tools.exception.CommandNotFoundException;
import fr.better.tools.listener.GuiListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class SimpleCommand extends BetterCommand {

    private Action param;

    public SimpleCommand(String name, Action argument) {
        try{
            this.param = argument;
            GuiListener.MAIN.getCommand(name).setExecutor(this);
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

        List<String> args = Arrays.asList(strings);
        param.run(commandSender, args, s);
        return true;
    }
}
