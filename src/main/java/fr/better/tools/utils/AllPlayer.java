package fr.better.tools.utils;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AllPlayer {

    private List<Player> players;

    public AllPlayer(List<Player> players) {
        this.players = players;
    }

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
}
