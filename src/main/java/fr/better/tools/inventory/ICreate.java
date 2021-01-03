package fr.better.tools.inventory;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;

public class ICreate {

    private ItemStack item;

    public ICreate(ItemStack it){
        this.item = it;
    }

    public ICreate(Material mat){
        this.item = new ItemStack(mat, 1);
    }

    public ICreate(Material mat, int size){
        this.item = new ItemStack(mat, size);
    }

    public ICreate(Material mat, int size, short data){
        this.item = new ItemStack(mat, size, data);
    }

    public ICreate setName(final String displayName) {
        ItemMeta itm = item.getItemMeta();
        itm.setDisplayName(displayName);
        item.setItemMeta(itm);
        return this;
    }

    public ICreate setLore(final List<String> lore) {
        ItemMeta itm = item.getItemMeta();
        itm.setLore(lore);
        item.setItemMeta(itm);
        return this;
    }

    public ICreate setLore(final String... lore) {
        return setLore(Arrays.asList(lore));
    }

    public ICreate addEnchant(final Enchantment enchant, final int i) {
        ItemMeta itm = item.getItemMeta();
        itm.addEnchant(enchant, i, false);
        item.setItemMeta(itm);
        return this;
    }

    public ICreate setUnbreakable(final boolean is) {
        ItemMeta itm = item.getItemMeta();
        itm.spigot().setUnbreakable(is);
        item.setItemMeta(itm);
        return this;
    }

    public ICreate setDurability(final int durability) {
        ItemMeta itm = item.getItemMeta();
        item.setDurability((short) durability);
        item.setItemMeta(itm);
        return this;
    }

    public ICreate setHead(final String owner) {
        item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skm = (SkullMeta) item.getItemMeta();
        skm.setOwner(owner);
        item.setItemMeta(skm);
        return this;
    }

    public ICreate setArmorColor(final Color color) {
        LeatherArmorMeta lam = (LeatherArmorMeta) item.getItemMeta();
        lam.setColor(color);
        item.setItemMeta(lam);
        return this;
    }

    public ItemStack build() {
        return item;
    }
}