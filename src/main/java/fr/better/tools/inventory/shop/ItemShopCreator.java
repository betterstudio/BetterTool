package fr.better.tools.inventory.shop;


import fr.better.tools.utils.ICreate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemShopCreator{

    private final ShopManager instance;

    private ItemStack view, content;
    private int sell, buy, slot;
    private boolean canSell, canBuy;

    public ItemShopCreator(ShopManager shopManager) {
        instance = shopManager;
    }

    public ItemShopCreator setSellPrice(int unit_price){
        canSell = true;
        sell = unit_price;
        return this;
    }

    public ItemShopCreator setBuyPrice(int unit_price){
        canBuy = true;
        buy = unit_price;
        return this;
    }

    public ItemShopCreator setSlot(int slot){
        this.slot = slot;
        return this;
    }

    public ItemShopCreator setViewItems(ItemStack item){

        view = item;

        return this;
    }

    public ItemShopCreator setContentItem(ItemStack item){
        this.content = item;
        return this;
    }

    public void build(){
        if(content == null){
            content = new ICreate(Material.BED).setName("§4ERROR").build();
        }
        if(view == null){
            view = content;
        }

        ItemMeta meta = view.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(meta.hasLore())
        for(String line : meta.getLore())lore.add(line);

        if(meta.hasLore()){
            lore.add(" §r ");
            lore.add("§8§m-----------------");
            lore.add(" §r ");
        }

        if(canBuy) lore.add("§7Acheter : §6" + buy);
        if(canSell)lore.add("§7Vendre : §6" + sell);

        meta.setLore(lore);
        view.setItemMeta(meta);

        instance.setItem(new ItemShop() {
            @Override
            public boolean canBuy() {
                return canBuy;
            }

            @Override
            public boolean canSell() {
                return canSell;
            }

            @Override
            public ItemStack getView() {
                return view;
            }

            @Override
            public ItemStack getItems() {
                return content;
            }

            @Override
            public int getSellPrice() {
                return sell;
            }

            @Override
            public int getBuyPrice() {
                return buy;
            }

            @Override
            public int getSlot() {
                return slot;
            }
        });
    }

}
