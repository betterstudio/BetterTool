package fr.better.tools.inventory.validate;

import fr.better.tools.inventory.GuiCreator;
import fr.better.tools.inventory.action.ClickAction;

import fr.better.tools.utils.ICreate;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ValidateGui extends GuiCreator {

    public ValidateGui(Player p, String why, VAction action, String valid, String deny) {
        super(why, 3);

        setTopLine(9);
        setBackLine(9);
        set(new ICreate(Material.STAINED_GLASS,1, (short) 5).setName(deny).build(), 9, 10, 11, 12);
        set(new ICreate(Material.STAINED_GLASS,1, (short) 14).setName(valid).build(), 14, 15, 16, 17);
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

    public ValidateGui(Player p, String why, VAction action, ItemStack valid, ItemStack deny) {
        super(why, 3);

        setTopLine(9);
        setBackLine(9);
        set(valid, 9, 10, 11, 12);
        set(deny, 14, 15, 16, 17);
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
