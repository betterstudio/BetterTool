package fr.better.tools.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Argument {

    private boolean dontNeedPlayer, takePlayerAsParameter;

    public abstract String execute(Player player, List<String> parameters);
    public abstract String parameter();
    public abstract String utility();
    public String permission() { return ""; }

    public void run(CommandSender sender, List<String> args){
        if(sender instanceof Player){
            if (hasRequiredParameter(args, 0)){
                execute((Player)sender, args);
            }
        }else{
            if(dontNeedPlayer){
                if(hasRequiredParameter(args,0)){
                    execute(null, args);
                }
            }else if(takePlayerAsParameter){
                if(hasRequiredParameter(args, 1)){
                    Player player = Bukkit.getPlayer(args.get(0));
                    if(player == null){
                        System.out.println("Error : player specified in command are null !");
                        return;
                    }
                    args.remove(0);
                    execute(player, args);
                }
            }else{
                System.out.println("Error : this command require a player as command sender !");
            }
        }
    }

    private boolean hasRequiredParameter(List<String> args, int plus){
        return Arrays.stream(parameter().split(" ")).filter(p -> { return p.startsWith("<"); }).count() <= args.size()+plus;
    }

    public String getUtility(){
        String r = "";
        if (takePlayerAsParameter){
            r  = r + " [Joueur]";
        }
        return r + " " + utility();
    }

    public void setDontNeedPlayer(boolean dontNeedPlayer) {
        this.dontNeedPlayer = dontNeedPlayer;
    }

    public void setTakePlayerAsParameter(boolean takePlayerAsParameter) {
        this.takePlayerAsParameter = takePlayerAsParameter;
    }
}
