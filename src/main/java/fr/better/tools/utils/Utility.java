package fr.better.tools.utils;

import fr.better.tools.config.Change;
import fr.better.tools.system.Instantiaters;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;

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

    public static int listAmmount(List<ItemStack> items){
        return items.stream().mapToInt(ItemStack::getAmount).sum();
    }

    public static List<String> replaceAll(List<String> list, Change... changes){
        List<String> all = new ArrayList<>();

        for(String part : list){
            String a = part;
            for(Change change : changes){
                a = part.replace(change.getWord(), change.getReplaced());
            }
            all.add(a);
        }
        return all;
    }

    public static List<ItemStack> toItemStackArray(int ammount, ItemStack base){

        List<ItemStack> returnItem = new ArrayList<>();

        int number_stack = ammount/64;
        int rest = ammount%64;

        ItemStack i = base.clone();
        i.setAmount(64);

        if(number_stack > 0){
            for(int n = 0; n <= number_stack; n++){
                returnItem.add(i.clone());
            }
        }

        if(rest > 0){
            i.setAmount(rest);
            returnItem.add(i.clone());
        }
        return returnItem;
    }

    public static String booleanToCharacter(boolean b) {
        if(b){
            return "§a✔";
        }else{
            return "§c✗";
        }
    }

    public static String timeToMinute(int seconds){
        int min = seconds/60;
        int sec = seconds%60;

        String ssec = sec + "";
        String smin = min + "";

        if(min < 10){
            smin = "0" + smin;
        }

        if(sec < 10){
            ssec = "0" + ssec;
        }

        return smin + ":" + ssec;
    }
}
