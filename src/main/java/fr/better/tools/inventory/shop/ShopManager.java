package fr.better.tools.inventory.shop;

import fr.better.tools.config.BetterConfig;
import fr.better.tools.config.MessageManager;
import fr.better.tools.deprecated.Instantiaters;
import fr.better.tools.inventory.GuiCreator;
import fr.better.tools.inventory.action.ClickAction;
import fr.better.tools.inventory.validate.VAction;
import fr.better.tools.inventory.validate.ValidateGui;
import fr.better.tools.utils.InventoryUtils;
import fr.better.tools.utils.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ShopManager extends GuiCreator {

    private List<ItemShop> items;

    public ShopManager(String name, int line) {
        super(name, line);
        items = new ArrayList<>();
        setAction(new ClickAction() {
            @Override
            public void action(InventoryClickEvent event) {
                event.setCancelled(true);

                Player p = (Player) event.getWhoClicked();

                ItemStack item = event.getCurrentItem();
                ItemShop shop = getInShop(item);

                if(shop == null)return;

                if(event.isRightClick()){
                    sell(p, shop);
                }else if(event.isLeftClick()){
                    buy(p, shop);
                }else if(event.isShiftClick()){
                    allSell(p, shop);
                }
            }
        });
    }

    public void buy(Player p, ItemShop shop) {

        MessageManager manager = new MessageManager((BetterConfig) Instantiaters.getPlugin().getBetterConfig());
        p.closeInventory();

        new DataGui(manager.getDataPrefix() + " " + manager.getBuy(), shop, p, (sizeValue) -> {
            new ValidateGui(p,manager.getValidatePrefix() + " " + manager.getBuy(), new VAction() {
                @Override
                public void doWhenAccept() {
                    int[] size = { sizeValue.getI() };
                    int money = Instantiaters.getManager().get(p);
                    int price = shop.getBuyPrice() * size[0];

                    if(money < price){
                        p.sendMessage(manager.getErrorMessageHasntMoney());
                        return;
                    }

                    Instantiaters.getManager().set(p, money-price);
                    p.sendMessage(manager.getMessageWhenBuy(size[0], shop.getItems().getType().name(), price));

                    p.closeInventory();

                    for(ItemStack item : Utility.toItemStackArray(size[0], shop.getItems())){
                        if(!new InventoryUtils(p.getInventory()).isFull()){
                            p.getInventory().addItem(item);
                        }else{
                            p.getWorld().dropItem(p.getLocation(), item);
                        }
                    }
                }
            }, manager.getAccept(), manager.getDeny());
        });
    }

    public void sell(Player p, ItemShop shop) {
        p.closeInventory();
        
        MessageManager manager = new MessageManager((BetterConfig) Instantiaters.getPlugin().getBetterConfig());

        new DataGui(manager.getDataPrefix() + " " + manager.getSell(), shop, p, (sizeValue) -> {

            final int[] size = {sizeValue.getI()};

            if(size[0] > Utility.listAmmount(new InventoryUtils(p.getInventory()).getItemWhenIsSimilar(shop.getItems()))){
                p.sendMessage(manager.getErrorMessageHasntItem());
                return;
            }

            new ValidateGui(p,manager.getValidatePrefix() + manager.getSell(), new VAction() {

                @Override
                public void doWhenAccept() {

                    int money = Instantiaters.getManager().get(p);
                    int price = shop.getSellPrice() * size[0];
                    for(int i = 0; i < p.getInventory().getSize(); i++){

                        try{
                            ItemStack item = p.getInventory().getItem(i);

                            if(item.isSimilar(shop.getItems())){

                                int amount = item.getAmount();

                                if(size[0] >= amount){
                                    size[0] = size[0] -amount;
                                    p.getInventory().clear(i);
                                    if(size[0] == amount)break;
                                }else{
                                    ItemStack it = shop.getItems().clone();
                                    it.setAmount(amount- size[0]);
                                    p.getInventory().setItem(i, it);
                                    break;
                                }
                            }

                        }catch (Exception e){

                        }
                    }

                    Instantiaters.getManager().set(p, money+price);
                    p.sendMessage(manager.getMessageWhenSell(size[0], shop.getItems().getType().name(), price));
                    p.closeInventory();

                }
            }, manager.getAccept(), manager.getDeny());
        });
    }

    public void allSell(Player p, ItemShop shop) {
        p.closeInventory();

        MessageManager manager = new MessageManager((BetterConfig) Instantiaters.getPlugin().getBetterConfig());
        ItemStack item = shop.getItems();

        List<ItemStack> contained = new InventoryUtils(p.getInventory()).getItemWhenIsSimilar(item);

        if(contained.isEmpty()){
            p.sendMessage(manager.getErrorMessageHasntAnyItem());
            return;
        }

        new ValidateGui(p, manager.getValidatePrefix() + " " + manager.getAllSell(), new VAction() {
            @Override
            public void doWhenAccept() {

                int money = Instantiaters.getManager().get(p);
                int price = shop.getSellPrice() * Utility.listAmmount(contained);

                Instantiaters.getManager().set(p, money+price);
                p.sendMessage(
                        new MessageManager((BetterConfig) Instantiaters.getPlugin().getBetterConfig())
                                .getMessageWhenSellAll(shop.getItems().getItemMeta().getDisplayName(), price)
                );

                for(ItemStack i : contained){
                    p.getInventory().remove(i);
                }
                p.closeInventory();
            }
        }, manager.getAccept(), manager.getDeny());
    }

    public ItemShopCreator addItem(){
        return new ItemShopCreator(this);
    }

    public void setItem(ItemShop itemShop) {
        items.add(itemShop);
        if(itemShop.getView().getAmount() == 0)itemShop.getView().setAmount(1);
        set(itemShop.getView(), itemShop.getSlot());
    }

    private ItemShop getInShop(ItemStack it){
        for(ItemShop shop : items){
            if(shop.getView().isSimilar(it))return shop;
        }
        return null;
    }
}