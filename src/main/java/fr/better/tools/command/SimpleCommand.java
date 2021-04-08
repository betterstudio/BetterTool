package fr.better.tools.command;

import com.google.inject.Inject;
import fr.better.tools.BetterPlugin;
import fr.better.tools.exception.CommandNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.Arrays;
import java.util.List;

public class SimpleCommand extends BetterCommand {

    private Argument param;

    @Inject
    private BetterPlugin plugin;

    private SimpleCommand(String name, Argument argument) {
        try{
            this.param = argument;
            plugin.getCommand(name).setExecutor(this);
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
        param.run(commandSender, args);
        return true;
    }

    public static class Builder{

        private Argument arg;
        public Builder(String name, Argument arg){
            this.arg = arg;
            new SimpleCommand(name, arg);
        }
        public void forConsole(){
            arg.setDontNeedPlayer(true);
        }
        public void playerAsArgs(){
            arg.setTakePlayerAsParameter(true);
        }
    }
}
