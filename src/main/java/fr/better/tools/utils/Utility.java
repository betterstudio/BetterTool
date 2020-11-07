package fr.better.tools.utils;

import fr.better.tools.deprecated.Instantiaters;
import org.bukkit.Bukkit;

import java.time.Instant;

public class Utility {

    public static String firstToUpper(String string){
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public static void async(Runnable runnable){
        Bukkit.getServer().getScheduler().runTaskAsynchronously(Instantiaters.getPlugin(), runnable);
    }

    public static void repeat(Runnable runnable, int cooldown){
        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(Instantiaters.getPlugin(), runnable, 0, cooldown);
    }

    public static void later(Runnable runnable, int delay){
        Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(Instantiaters.getPlugin(), runnable, delay);
    }
}
