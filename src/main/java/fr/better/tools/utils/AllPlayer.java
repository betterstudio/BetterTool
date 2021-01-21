package fr.better.tools.utils;

import fr.better.tools.visual.ActionBar;
import fr.better.tools.visual.Title;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.PotionEffect;

import java.util.*;

public class AllPlayer {

    private List<Player> players;

    public AllPlayer(List<Player> players) {
        this.players = players;
    }

    //////////////////////////////////////////////////////////////////

    public void teleport(Location loc){
        players.forEach((p) -> { p.teleport(loc); });
    }

    public void openInventory(Inventory inventory){
        players.forEach((p) -> { p.openInventory(inventory); });
    }

    public void addItems(ItemStack... stack){
        players.forEach((p) -> { p.getInventory().addItem(stack); });
    }

    public void sendMessage(String message){
        players.forEach((p) -> { p.sendMessage(message); });
    }

    public void setGameMode(GameMode mode){
        players.forEach((p) -> { p.setGameMode(mode); });
    }

    public void kick(String message){
        players.forEach((p) -> { p.kickPlayer(message); });
    }

    public void setHealth(double health) {

        players.forEach((p) -> {p.setHealth(health);});
    }

    public void addPotionEffect(PotionEffect potionEffect){
        for(Player player : players) {
            player.addPotionEffect(potionEffect);
        }
    }

    public void setMaxHealth(double health) {
        players.forEach((p) -> {p.setMaxHealth(health);});
    }

    public void resetMaxHealth() {
        players.forEach((p) -> {
            p.setMaxHealth(20);
        });
    }

    public void setOp(boolean value) {
        players.forEach((p) -> {p.setOp(value);});
    }

    public void setLevel(int value) {
        players.forEach((p) -> {p.setLevel(value);});
    }

    public void setExp(float value) {
        players.forEach((p) -> {p.setExp(value);});
    }

    public void sendActionBar(String bar){
        players.forEach((p) -> {new ActionBar(p).send(bar);});
    }

    public void sendTitle(String title, String sub){
        players.forEach((p) -> {new Title(p).send(title, sub, 20, 50, 20);});
    }
}
