package fr.better.tools.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public abstract class Action {

    protected boolean dontNeedPlayer, takePlayerAsParameter;

    public abstract String execute(Player player, List<String> parameters);
    public abstract String parameter();
    public String permission() { return ""; }

    public void run(CommandSender sender, List<String> args, String cmd){
        if(sender instanceof Player){
            if (hasRequiredParameter(args, 0)){
                sender.sendMessage(execute((Player)sender, args));
            }else{
                sender.sendMessage("§cLa commande correcte est : /" + cmd + " " + parameter());
            }
        }else{
            if(dontNeedPlayer){
                if(hasRequiredParameter(args,0)){
                    sender.sendMessage(execute(null, args));
                }else{
                    sender.sendMessage("§cLa commande correcte est : /" + cmd + " " + parameter());
                }
            }else if(takePlayerAsParameter){
                if(hasRequiredParameter(args, 1)){
                    Player player = Bukkit.getPlayer(args.get(0));
                    if(player == null){
                        System.out.println("Error : player specified in command are null !");
                        return;
                    }
                    args.remove(0);
                    player.sendMessage(execute(player, args));
                }else{
                    sender.sendMessage("§cLa commande correcte est : /" + cmd + " " + parameter());
                }
            }else{
                System.out.println("Error : this command require a player as command sender !");
            }
        }
    }

    private boolean hasRequiredParameter(List<String> args, int plus){
        System.out.println(Arrays.stream(parameter().split(" ")).filter(p -> { return p.startsWith("<"); }).count());
        System.out.println(args.size());
        return Arrays.stream(parameter().split(" ")).filter(p -> { return p.startsWith("<"); }).count()+plus <= args.size();
    }

    public void setDontNeedPlayer(boolean dontNeedPlayer) {
        this.dontNeedPlayer = dontNeedPlayer;
    }

    public void setTakePlayerAsParameter(boolean takePlayerAsParameter) {
        this.takePlayerAsParameter = takePlayerAsParameter;
    }
}
