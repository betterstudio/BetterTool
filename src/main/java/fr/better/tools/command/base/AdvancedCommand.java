package fr.better.tools.command.base;

import fr.better.tools.command.content.Action;
import fr.better.tools.command.content.Argument;
import fr.better.tools.exception.CommandNotFoundException;
import fr.better.tools.listener.GuiListener;
import fr.better.tools.utils.Utility;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvancedCommand implements CommandExecutor, Command {

    private final String commandName;
    private final Map<String, Argument> arguments;

    public AdvancedCommand(String commandName){
        this.commandName = commandName;
        this.arguments = new HashMap<>();
        try{
            GuiListener.MAIN.getCommand(commandName).setExecutor(this);
        }catch(NullPointerException e){
            try {
                throw new CommandNotFoundException(e.getCause());
            } catch (CommandNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {

        if(strings.length > 0 && !strings[0].equalsIgnoreCase("help")) {
            String p1 = strings[0];
            if(!arguments.containsKey(p1)){
                commandSender.sendMessage("§cErreur ! La commande que tu veux faire n'existe pas !");
                return false;
            }

            Argument args = arguments.get(p1);
            List<String> param = new ArrayList<>();

            for (String part : strings) {
                if(part.equalsIgnoreCase(strings[0]))continue;
                param.add(part);
            }

            args.run(commandSender, param, s + "" + strings[0]);

        }else{
            senHelpMessage(commandSender);
        }
        return false;
    }

    @Override
    public Builder add(String name, Argument arg) {
        return new AdvancedCommand.Builder(this, name, arg);
    }

    @Override
    public void senHelpMessage(CommandSender sender) {

        String cmd = Utility.firstToUpper(commandName);
        if(cmd.length() < 4)cmd = cmd.toUpperCase();

        if(sender instanceof Player){
            sender.sendMessage(BetterCommand.getMainColor() + "Command : " + cmd);
            sender.sendMessage("§8§m-----------------------");
            for(Map.Entry<String, Argument> entry : arguments.entrySet()){
                Argument param = entry.getValue();
                String perm = param.permission();
                if(sender instanceof Player && !sender.hasPermission(perm))continue;
                sender.sendMessage(BetterCommand.getSecondColor()
                        + " • /" + commandName + " " + BetterCommand.getMainColor() + entry.getKey()
                        + " " + param.parameter() + " " + BetterCommand.getSecondColor()  + param.getUtility());
            }
            sender.sendMessage("§8§m-----------------------");
            String who = GuiListener.MAIN.getDescription().getAuthors().get(0);
            if(who != null && !who.isEmpty())
                sender.sendMessage(BetterCommand.getMainColor() + "By " + BetterCommand.getWho());
        }else{
            System.out.println("Flemme mdr !");
        }
    }

    public static class Builder{
        private Action arg;
        public Builder(AdvancedCommand cmd, String name, Argument arg){
            this.arg = arg;
            cmd.arguments.put(name, arg);
        }
        public void forConsole(){
            arg.setDontNeedPlayer(true);
        }
        public void playerAsArgs(){
            arg.setTakePlayerAsParameter(true);
        }
    }
}
