package fr.better.tools.inventory.shop;

import org.bukkit.inventory.ItemStack;

public interface ItemShop {

    boolean canBuy();
    boolean canSell();

    ItemStack getView();
    ItemStack getItems();

    int getSellPrice();
    int getBuyPrice();

    int getSlot();
}
