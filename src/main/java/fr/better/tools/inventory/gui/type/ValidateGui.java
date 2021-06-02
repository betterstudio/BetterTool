package fr.better.tools.inventory.gui.type;

import fr.better.tools.inventory.ItemCreate;
import fr.better.tools.inventory.gui.Gui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ValidateGui extends Gui {

    public interface VAction {

        void doWhenAccept();
        default void doWhenDeny(){}
    }

    public ValidateGui(String guiName, VAction action, String validName, String denyName) {
        super(guiName, 3);

        setTopLine(9);
        setBackLine(9);
        set(new ItemCreate(Material.STAINED_GLASS,1, (short) 5).setName(validName).build(), 9, 10, 11, 12);
        set(new ItemCreate(Material.STAINED_GLASS,1, (short) 14).setName(denyName).build(), 14, 15, 16, 17);
        click((e,p) -> {
                int slot = e.getSlot();
                e.setCancelled(true);
                if(Arrays.asList(9, 10, 11, 12).contains(slot)){
                    action.doWhenAccept();
                }else if(Arrays.asList(14, 15, 16, 17).contains(slot)){
                    action.doWhenDeny();
                }
        });
    }

    public ValidateGui(String guiName, VAction action, ItemStack valid, ItemStack deny) {
        super(guiName, 3);

        setTopLine(9);
        setBackLine(9);
        set(valid, 9, 10, 11, 12);
        set(deny, 14, 15, 16, 17);
        click((e,p) -> {
                int slot = e.getSlot();
                e.setCancelled(true);
                if(Arrays.asList(9, 10, 11, 12).contains(slot)){
                    action.doWhenAccept();
                }else if(Arrays.asList(14, 15, 16, 17).contains(slot)){
                    action.doWhenDeny();
                }
        });
    }
}
