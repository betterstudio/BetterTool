package fr.better.tools.inventory.gui;

import fr.better.tools.inventory.ItemCreate;
import fr.better.tools.listener.GuiListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Gui {

    private final Inventory inventory;
    private BiConsumer<InventoryClickEvent, Player> click;
    private BiConsumer<InventoryCloseEvent, Player> close;

    public Gui(String name, int line) {
        inventory = Bukkit.createInventory(null, line*9, name);
    }

    private Gui(Inventory inventory){
        this.inventory = inventory;
    }

    public static Gui asGui(Inventory inv){
        return new Gui(inv);
    }

    public Gui open(Player player) {
        GuiListener.INSTANCE.registerGui(this, player);
        player.openInventory(inventory);
        return this;
    }

    public String name() {
        return inventory.getTitle();
    }

    public Gui click(BiConsumer<InventoryClickEvent, Player> action){
        this.click = action;
        return this;
    }

    public Gui close(BiConsumer<InventoryCloseEvent, Player> action){
        this.close = action;
        return this;
    }

    public Gui click(Consumer<InventoryClickEvent> action){
        this.click = (event, player) -> action.accept(event);
        return this;
    }

    public Gui close(Consumer<InventoryCloseEvent> action){
        this.close = (event, player) -> action.accept(event);
        return this;
    }

    public Gui setClickCancelled(){
        this.click = (e,p) -> {
            e.setCancelled(true);
        };
        return this;
    }

    public Gui setCantClose(){
        this.close = (e,p) -> {
            e.getPlayer().openInventory(inventory);
        };
        return this;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public BiConsumer<InventoryClickEvent, Player> getClick() {
        return click;
    }

    public BiConsumer<InventoryCloseEvent, Player> getClose() {
        return close;
    }

    public Gui setTopLine(int data){
        setTopLine(new ItemCreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build());
        return this;
    }

    public Gui setTopLine(ItemStack item) {
        for(int i = 0; i < 9; i++){
            inventory.setItem(i, item);
        }
        return this;
    }

    public Gui setBackLine(int data){
        setBackLine(new ItemCreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build());
        return this;
    }

    public Gui setBackLine(ItemStack item){
        for(int i = inventory.getSize()-9; i < inventory.getSize(); i++){
            inventory.setItem(i, item);
        }
        return this;
    }

    public Gui setThePerfect(int data){
        try{
            ItemStack item = new ItemCreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build();

            Arrays.asList(0, 1, 2, 6, 7, 8, 9, 17, 36, 45, 46, 47, 48, 52, 53, 54).forEach(in -> {
                inventory.setItem(in,  item);
            });
        }catch(Exception ex){
            System.out.println("You can use that uniquely on a 6 line sized chest !");
        }
        return this;
    }

    public Gui setFull(ItemStack what){
        for(int i = 0; i < inventory.getSize(); i++){
            inventory.setItem(i, what);
        }
        return this;
    }

    public Gui setFull(int color){
        for(int i = 0; i < inventory.getSize(); i++){
            inventory.setItem(i,  new ItemCreate(Material.STAINED_GLASS_PANE, 1, (short) color).setName(" ").build());
        }
        return this;
    }

    public Gui setSideWays(ItemStack item){
        switch(inventory.getSize()/9){
            case 6:
                inventory.setItem(45, item);
                inventory.setItem(53, item);
            case 5:
                inventory.setItem(36, item);
                inventory.setItem(44, item);
            case 4:
                inventory.setItem(27, item);
                inventory.setItem(35, item);
            case 3:
                inventory.setItem(18, item);
                inventory.setItem(26, item);
            case 2:
                inventory.setItem(9, item);
                inventory.setItem(17, item);
            case 1:
                inventory.setItem(0, item);
                inventory.setItem(8, item);
                break;
        }
        return this;
    }

    public Gui setSideWays(int data){
        setSideWays(new ItemCreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build());
        return this;
    }

    public Gui set(ItemStack what, int... where){
        for(int i : where){
            inventory.setItem(i, what);
        }
        return this;
    }

    public Gui add(ItemStack... item){
        for(ItemStack i : item)inventory.addItem(i);
        return this;
    }

    public Inventory give() {
        return inventory;
    }
}
