package fr.better.tools.system;

import fr.better.tools.inventory.gui.Gui;
import fr.better.tools.inventory.gui.invAction.CloseAction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class BListener implements Listener {

    private final List<Gui> all;

    public BListener(){
        all = new ArrayList<>();
        Instantiaters.setListener(this);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){

        Inventory inventory = event.getClickedInventory();
        Gui gui = systemGetGuiByInventory(inventory);

        if(gui == null)return;

        try{
            ((ClickAction)gui.getAction(ActionType.CLICK)).action(event);
        }catch(Exception ignored){ }

    }

    @EventHandler
    public void onDrag(InventoryDragEvent event){

        Inventory inventory = event.getInventory();
        Gui gui = systemGetGuiByInventory(inventory);

        if(gui == null)return;

        try{
            ((DragAction)gui.getAction(ActionType.DRAG)).action(event);
        }catch(Exception ignored){ }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){

        Inventory i = e.getInventory();
        Gui g = systemGetGuiByInventory(i);

        if(g == null)return;

        try{
            ((CloseAction) g.getAction(ActionType.CLOSE)).action(e);
        }catch(Exception ignored){ }

        systemUnRegisterGui(g);
    }

    @Deprecated
    public void systemRegisterGui(Gui gui) {
        all.add(gui);
    }

    @Deprecated
    private void systemUnRegisterGui(Gui gui) {
        all.remove(gui);
    }

    @Deprecated
    private Gui systemGetGuiByInventory(Inventory inventory) {
        for(Gui gui : all){
            if(gui.give().equals(inventory))return gui;
        }
        return null;
    }
}
