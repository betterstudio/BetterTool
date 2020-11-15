package fr.better.tools.inventory.shop;

import org.bukkit.entity.Player;

public interface MoneyProvider {

    int get(Player player);
    void set(Player player, int money);

}
