package fr.better.tools.command.base;

import fr.better.tools.command.content.Argument;
import fr.better.tools.exception.CommandNotFoundException;
import fr.better.tools.listener.GuiListener;
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
    private final BetterCommand command;

    public AdvancedCommand(String commandName, BetterCommand command){
        this.commandName = commandName;
        this.command = command;
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
                StringBuilder builder = new StringBuilder();
                arguments.keySet().forEach(keys -> builder.append("/").append(keys));
                commandSender.sendMessage(this.command.getErrorArgument().apply(builder.append(">").toString().replaceFirst("/", "<")));
                return false;
            }

            Argument args = arguments.get(p1);
            List<String> param = new ArrayList<>();

            for (String part : strings) {
                if(part.equalsIgnoreCase(strings[0]))continue;
                param.add(part);
            }

            args.run(commandSender, param, s + " " + strings[0], this.command);

        }else
            sendHelpMessage(commandSender);
        return false;
    }

    @Override
    public void add(String name, Argument arg, ParticularityType type) {
        arguments.put(name, arg);
    }

    @Override
    public void add(String name, Argument arg){
        arguments.put(name, arg);
    }

    @Override
    public void sendHelpMessage(CommandSender sender) {

        String cmd = Character.toUpperCase(commandName.charAt(0)) + commandName.substring(1);
        if(cmd.length() < 4)cmd = cmd.toUpperCase();

        sender.sendMessage(command.getMainColor() + "Command : " + cmd);
        sender.sendMessage("§8§m-----------------------");

        arguments.forEach((token, arguments) -> {
            String perm = arguments.permission();
            if(sender instanceof Player && !sender.hasPermission(perm))return;
            sender.sendMessage(command.getMainColor()
                    + " • /" + commandName + " " + command.getSecondColor() + token
                    + " " + arguments.parameter()  + arguments.getUtility(!(sender instanceof Player)));
        });

        sender.sendMessage("§8§m-----------------------");
        String who = GuiListener.MAIN.getDescription().getAuthors().get(0);
        sender.sendMessage(command.getMainColor() + "By " + who);
    }
}
