package fr.better.tools.command;

import fr.better.tools.exception.CommandNotFoundException;
import fr.better.tools.system.BListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class SimpleCommand extends BetterCommand {

    private Action param;

    private SimpleCommand(String name, Action argument) {
        try{
            this.param = argument;
            BListener.MAIN.getCommand(name).setExecutor(this);
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

    public static class Builder{

        private Action arg;
        public Builder(String name, Action arg){
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
