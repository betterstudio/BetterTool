package fr.better.tools.inventory;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ItemCreate {

    private ItemStack item;

    public ItemCreate(ItemStack it){
        this.item = it;
    }

    public ItemCreate(Material mat){
        this.item = new ItemStack(mat, 1);
    }

    public ItemCreate(Material mat, int size){
        this.item = new ItemStack(mat, size);
    }

    public ItemCreate(Material mat, int size, short data){
        this.item = new ItemStack(mat, size, data);
    }

    public ItemCreate setName(final String displayName) {
        ItemMeta itm = item.getItemMeta();
        itm.setDisplayName(displayName);
        item.setItemMeta(itm);
        return this;
    }

    public ItemCreate setLore(final List<String> lore) {
        ItemMeta itm = item.getItemMeta();
        itm.setLore(lore);
        item.setItemMeta(itm);
        return this;
    }

    public ItemCreate setLore(final String... lore) {
        return setLore(Arrays.asList(lore));
    }

    public ItemCreate addEnchant(final Enchantment enchant, final int i) {
        ItemMeta itm = item.getItemMeta();
        itm.addEnchant(enchant, i, true);
        item.setItemMeta(itm);
        return this;
    }

    public ItemCreate setBannerPatern(DyeColor color, Pattern... patterns){
        BannerMeta meta = (BannerMeta) item.getItemMeta();
        meta.setPatterns(Arrays.asList(patterns));
        meta.setBaseColor(color);
        item.setItemMeta(meta);
        return this;
    }

    public ItemCreate setBannerPatern(DyeColor color, List<Pattern> patterns){
        BannerMeta meta = (BannerMeta) item.getItemMeta();
        meta.setPatterns(patterns);
        meta.setBaseColor(color);
        item.setItemMeta(meta);
        return this;
    }

    public ItemCreate setUnbreakable(final boolean is) {
        ItemMeta itm = item.getItemMeta();
        itm.spigot().setUnbreakable(is);
        item.setItemMeta(itm);
        return this;
    }

    public ItemCreate setDurability(final int durability) {
        ItemMeta itm = item.getItemMeta();
        item.setDurability((short) durability);
        item.setItemMeta(itm);
        return this;
    }

    public ItemCreate setHead(final String owner) {
        item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skm = (SkullMeta) item.getItemMeta();
        skm.setOwner(owner);
        item.setItemMeta(skm);
        return this;
    }

    public ItemCreate setHeadBase64(final String base64) {
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", base64));
        Field profileField = null;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        item.setItemMeta(meta);
        return this;
    }

    public ItemCreate addEnchantInABook(Enchantment enchant, int lvl){
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchant, lvl, true);
        item.setItemMeta(meta);
        return this;
    }

    public ItemCreate setArmorColor(final Color color) {
        LeatherArmorMeta lam = (LeatherArmorMeta) item.getItemMeta();
        lam.setColor(color);
        item.setItemMeta(lam);
        return this;
    }

    public ItemStack build() {
        return item;
    }
}
