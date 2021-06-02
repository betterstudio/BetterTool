package fr.better.tools.inventory.gui;

import fr.better.tools.inventory.ItemCreate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.function.BiConsumer;

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

    public void open(Player player) {
        player.openInventory(inventory);
    }

    public String name() {
        return inventory.getTitle();
    }

    public void click(BiConsumer<InventoryClickEvent, Player> action){
        this.click = action;
    }

    public void close(BiConsumer<InventoryCloseEvent, Player> action){
        this.close = action;
    }

    public void setClickCancelled(){
        this.click = (e,p) -> {
            e.setCancelled(true);
        };
    }

    public void setCantClose(){
        this.close = (e,p) -> {
            e.getPlayer().openInventory(inventory);
        };
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

    public void setTopLine(int data){
        setTopLine(new ItemCreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build());
    }

    public void setTopLine(ItemStack item){
        for(int i = 0; i < 9; i++){
            inventory.setItem(i, item);
        }
    }

    public void setBackLine(int data){
        setBackLine(new ItemCreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build());
    }

    public void setBackLine(ItemStack item){
        for(int i = inventory.getSize()-9; i < inventory.getSize(); i++){
            inventory.setItem(i, item);
        }
    }

    public void setThePerfect(int data){
        try{
            ItemStack item = new ItemCreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build();

            Arrays.asList(0, 1, 2, 6, 7, 8, 9, 17, 36, 45, 46, 47, 48, 52, 53, 54).forEach(in -> {
                inventory.setItem(in,  item);
            });
        }catch(Exception ex){
            System.out.println("You can use that uniquely on a 6 line sized chest !");
        }
    }

    public void setFull(ItemStack what){
        for(int i = 0; i < inventory.getSize(); i++){
            inventory.setItem(i, what);
        }
    }

    public void setFull(int color){
        for(int i = 0; i < inventory.getSize(); i++){
            inventory.setItem(i,  new ItemCreate(Material.STAINED_GLASS_PANE, 1, (short) color).setName(" ").build());
        }
    }

    public void setSideWays(ItemStack item){
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
    }

    public void setSideWays(int data){
        setSideWays(new ItemCreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build());
    }

    public void set(ItemStack what, int... where){
        for(int i : where){
            inventory.setItem(i, what);
        }
    }

    public void add(ItemStack... item){
        for(ItemStack i : item)inventory.addItem(i);
    }

    public Inventory give() {
        return inventory;
    }

    public static class Builder{
        private final Gui gui;

        public Builder(Gui gui) {
            this.gui = gui;
        }

        public Builder topLine(int data){
            gui.setTopLine(data);return this;
        }

        public Builder backline(int data){
            gui.setBackLine(data);return this;
        }

        public Builder sideway(int data){
            gui.setSideWays(data);return this;
        }

        public Builder set(ItemStack stack, int slot){
            gui.set(stack, slot);return this;
        }

        public Builder add(ItemStack stack){
            gui.add(stack);return this;
        }

        public Builder full(int data){
            gui.setFull(data);return this;
        }

        public Builder click(BiConsumer<InventoryClickEvent, Player> event){
            gui.click(event);return this;
        }

        public Builder close(BiConsumer<InventoryClickEvent, Player> event){
            gui.click(event);return this;
        }

        public Builder cantClose(){
            gui.setCantClose();return this;
        }

        public Builder cantClick(){
            gui.setClickCancelled();return this;
        }

        public Gui get(){return gui;}
    }
}
