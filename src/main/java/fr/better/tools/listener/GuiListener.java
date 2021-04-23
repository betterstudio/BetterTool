package fr.better.tools.listener;

import fr.better.tools.BetterPlugin;
import fr.better.tools.inventory.gui.GuiCreator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GuiListener implements Listener {

    public static GuiListener INSTANCE;
    public static BetterPlugin MAIN;
    private final List<GuiCreator> all;

    public GuiListener(BetterPlugin plugin){
        all = new ArrayList<>();
        MAIN = plugin;
        INSTANCE = this;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){

        GuiCreator gui = systemGetGuiByInventory(e.getClickedInventory());

        if(gui == null)return;
        if(gui.getClick() == null)return;

        try{
            gui.getClick().accept(e);
        }catch(Exception ignored){ }

    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){

        GuiCreator gui = systemGetGuiByInventory(e.getInventory());

        if(gui == null)return;
        if(gui.getClose() == null)return;
        try{
            gui.getClose().accept(e);
        }catch(Exception ignored){ }

        unregisterGui(gui);
    }

    public void registerGui(GuiCreator gui) {
        all.add(gui);
    }

    public void unregisterGui(GuiCreator gui) {
        all.remove(gui);
    }

    private GuiCreator systemGetGuiByInventory(Inventory inventory) {
        for(GuiCreator gui : all){
            if(gui.give().equals(inventory))return gui;
        }
        return null;
    }
}
