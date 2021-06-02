package fr.better.tools.command.content;

import fr.better.tools.command.base.BetterCommand;
import fr.better.tools.command.base.ParticularityType;
import fr.better.tools.listener.GuiListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Arrays;
import java.util.List;

public abstract class Action {

    protected ParticularityType type;

    public abstract String execute(Player player, List<String> parameters);
    public abstract String parameter();
    public String permission() { return ""; }

    public void run(CommandSender sender, List<String> args, String cmd, BetterCommand command){

        if(sender instanceof Player){
            switch (type){
                case NEED_PLAYER:
                case TAKE_PLAYER_AS_ARG:
                    if (hasRequiredParameter(args, 0)){
                        sender.sendMessage(execute((Player)sender, args));
                    }else{
                        sender.sendMessage(command.getErrorParameter().apply(cmd + " " + parameter()));
                    }
                    break;

                case NEED_PLAYER_AS_ARG:
                    if (hasRequiredParameter(args, 1)){
                        Player player = Bukkit.getPlayer(args.get(0));
                        if(player == null){
                            System.out.println("Error : player specified in command are null !");
                            return;
                        }
                        args.remove(0);
                        sender.sendMessage(execute(player, args));
                    }else{
                        sender.sendMessage(command.getErrorParameter().apply(cmd + " " + parameter()));
                    }
                    break;

                case ONLY_CONSOLE:
                    sender.sendMessage(GuiListener.MAIN.getCommand(cmd).getPermissionMessage());
                    break;
            }
        }else switch(type){
                case ONLY_CONSOLE:
                    if (hasRequiredParameter(args, 0)){
                        sender.sendMessage(execute(null, args));
                    }else{
                        sender.sendMessage(command.getErrorParameter().apply(cmd + " " + parameter()));
                    }
                    break;
                case TAKE_PLAYER_AS_ARG:
                case NEED_PLAYER_AS_ARG:
                    if (hasRequiredParameter(args, 1)){
                        Player player = Bukkit.getPlayer(args.get(0));
                        if(player == null){
                            System.out.println("Error : player specified in command are null !");
                            return;
                        }
                        args.remove(0);
                        sender.sendMessage(execute(player, args));
                    }else{
                        sender.sendMessage(command.getErrorParameter().apply(cmd + " " + parameter()));
                    }
                    break;
                case NEED_PLAYER:
                    sender.sendMessage("This is only a player's command !");
                    break;
        }
    }

    private boolean hasRequiredParameter(List<String> args, int plus){
        return Arrays.stream(parameter().split(" ")).filter(p -> p.startsWith("<")).count()+plus <= args.size();
    }

    public void setType(ParticularityType type) {
        this.type = type;
    }
}
