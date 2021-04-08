package fr.better.tools;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import java.util.HashMap;

public class BetterBukkit {
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
