package fr.better.tools;

import fr.better.tools.visual.ActionBar;
import fr.better.tools.visual.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BetterBukkit {

    public static void sendActionBarToAllPlayers(String message) {
        sendActionBarToAllPlayers(message, -1);
    }

    public static void sendActionBarToAllPlayers(String message, int duration) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            new ActionBar(p).send(message, duration);
        }
    }

    public static void sendTitleToAllPlayers(String title, String sub){
        for (Player p : Bukkit.getOnlinePlayers()) {
            new Title(p).send(title, sub, 3, 16, 3);
        }
    }
}
