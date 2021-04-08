package fr.better.tools.utils;

import fr.better.tools.config.Change;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Utility {

    private static final String
            F = "⬆",
            FL = "⬉",
            L = "⬅",
            BL ="⬋",
            B = "⬇",
            BR = "⬊",
            R = "➞",
            FR = "⬈";

    public static String firstToUpper(String string){
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
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

    public static String asArrow(Location first, Location direction) {
        String arrow = "nop";

        Vector a = direction.toVector().setY(0).subtract(first.toVector().setY(0));
        Vector b = first.getDirection().setY(0);

        double angleDir = (Math.atan2(a.getZ(), a.getX()) / 2 / Math.PI * 360 + 360) % 360, angleLook = (Math.atan2(b.getZ(), b.getX()) / 2 / Math.PI * 360 + 360) % 360, angle = (angleDir - angleLook + 360) % 360;

        if (angle <= 022.5 || angle > 337.5) arrow = F;
        else if (angle <= 337.5 && angle > 292.5) arrow = FL;
        else if (angle <= 292.5 && angle > 247.5) arrow = L;
        else if (angle <= 347.5 && angle > 202.0) arrow = BL;
        else if (angle <= 202.0 && angle > 157.5) arrow = B;
        else if (angle <= 157.5 && angle > 112.5) arrow = BR;
        else if (angle <= 112.5 && angle > 067.5) arrow = R;
        else if (angle <= 067.5 && angle > 022.5) arrow = FR;

        return arrow;
    }
}
