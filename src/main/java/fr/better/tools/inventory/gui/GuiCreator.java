package fr.better.tools.inventory.gui;

import fr.better.tools.system.BListener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import javax.inject.Inject;
import java.util.function.Consumer;

public class GuiCreator extends GuiManager {

    private Consumer<InventoryClickEvent> click;
    private Consumer<InventoryCloseEvent> close;

    @Inject
    private BListener listener;

    public GuiCreator(String name, int line) {
        super(name, line);
        listener.registerGui(this);
    }

    private GuiCreator(Inventory inventory) {
        super(inventory);
        listener.unregisterGui(this);
    }

    public static GuiCreator asGui(Inventory inv){
        return new GuiCreator(inv);
    }

    public Inventory give() {
        return inventory;
    }

    public String name() {
        return inventory.getTitle();
    }

    public void click(Consumer<InventoryClickEvent> action){
        this.click = action;
    }

    public void close(Consumer<InventoryCloseEvent> action){
        this.close = action;
    }

    public void setClickCancelled(){
        this.click = e -> {
            e.setCancelled(true);
        };
    }

    public void setCantClose(){
        this.close = e -> {
            e.getPlayer().openInventory(inventory);
        };
    }

    public Consumer<InventoryClickEvent> getClick() {
        return click;
    }

    public Consumer<InventoryCloseEvent> getClose() {
        return close;
    }
}
