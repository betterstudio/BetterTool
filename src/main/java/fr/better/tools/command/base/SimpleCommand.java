package fr.better.tools.command.base;

import fr.better.tools.command.content.Action;
import fr.better.tools.exception.CommandNotFoundException;
import fr.better.tools.listener.GuiListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class SimpleCommand implements CommandExecutor {

    private final BetterCommand command;
    private final Action param;

    public SimpleCommand(String name, Action argument, BetterCommand command) throws CommandNotFoundException {
        try{
            this.param = argument;
            this.command = command;
            GuiListener.MAIN.getCommand(name).setExecutor(this);
        }catch(NullPointerException e){
            throw new CommandNotFoundException(e.getCause());
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> args = Arrays.asList(strings);
        param.run(commandSender, args, s, this.command);
        return true;
    }
}
