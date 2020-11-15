package fr.better.tools.inventory.validate;

import fr.better.tools.inventory.GuiCreator;
import fr.better.tools.inventory.action.ClickAction;
import fr.better.tools.inventory.action.CloseAction;
import fr.better.tools.utils.ICreate;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Arrays;

public class ValidateGui extends GuiCreator {

    public ValidateGui(Player p, String why, VAction action) {
        super(why, 3);
        setTopLine(9);
        setBackLine(9);
        set(new ICreate(Material.STAINED_GLASS,1, (short) 5).setName("§aACCEPTER").build(), 9, 10, 11, 12);
        set(new ICreate(Material.STAINED_GLASS,1, (short) 14).setName("§4ANNULER").build(), 14, 15, 16, 17);
        setAction(new ClickAction() {
            @Override
            public void action(InventoryClickEvent event) {
                int slot = event.getSlot();
                event.setCancelled(true);
                if(Arrays.asList(9, 10, 11, 12).contains(slot)){
                    action.doWhenAccept();
                }else if(Arrays.asList(14, 15, 16, 17).contains(slot)){
                    action.doWhenDeny();
                }
            }
        });
        p.openInventory(inventory);
    }
}
