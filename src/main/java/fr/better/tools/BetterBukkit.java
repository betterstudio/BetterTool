package fr.better.tools;

import fr.better.tools.visual.ActionBar;
import fr.better.tools.visual.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import java.util.HashMap;

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

    public static void give(Player player, HashMap<Integer, ItemStack> stuff) {
        for(Integer i : stuff.keySet()){
            switch(i){
                case 100:
                    player.getInventory().setBoots(stuff.get(i));
                    break;
                case 101:
                    player.getInventory().setLeggings(stuff.get(i));
                    break;
                case 102:
                    player.getInventory().setChestplate(stuff.get(i));
                    break;
                case 103:
                    player.getInventory().setHelmet(stuff.get(i));
                    break;
                default:
                    player.getInventory().setItem(i, stuff.get(i));
                    break;
            }
        }
    }

    public static void removePotion(Player p) {
        for (PotionEffect effect : p.getActivePotionEffects())
            p.removePotionEffect(effect.getType());
    }

    public static void clear(Player player){
        player.getInventory().setArmorContents(null);
        player.getInventory().clear();
    }
}
