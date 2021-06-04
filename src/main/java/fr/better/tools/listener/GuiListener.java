package fr.better.tools.listener;

import fr.better.tools.BetterPlugin;
import fr.better.tools.inventory.gui.Gui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GuiListener implements Listener {

    public static GuiListener INSTANCE;
    public static BetterPlugin MAIN;
    private final Map<Player, Gui> all;

    public GuiListener(BetterPlugin plugin){
        all = new HashMap<>();
        MAIN = plugin;
        INSTANCE = this;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){

        Player player = (Player) e.getWhoClicked();

        Optional<Gui> gui = getGuiByInventory(e.getClickedInventory(), player);
        if(!gui.isPresent())return;
        if(gui.get().getClick() == null)return;
        try{
            gui.get().getClick().accept(e, player);
        }catch(Exception ignored){}
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){

        Player player = (Player) event.getPlayer();

        Optional<Gui> gui = getGuiByInventory(event.getInventory(), player);

        if(!gui.isPresent())return;

        if(gui.get().getClose() == null)return;
        try{
            gui.get().getClose().accept(event, player);
        }catch(Exception ignored){ }

        all.remove(player);
    }

    public void registerGui(Gui gui, Player player) {
        all.put(player, gui);
    }

    private Optional<Gui> getGuiByInventory(Inventory inventory, Player player) {
        System.out.println(all.toString());
        return all.entrySet().stream().filter(entry -> entry.getValue().getInventory() == inventory && entry.getKey().equals(player))
                .map(Map.Entry::getValue).findFirst();
    }
}
