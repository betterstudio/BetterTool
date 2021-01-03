package fr.better.tools.inventory.gui.invAction;

import org.bukkit.event.inventory.InventoryCloseEvent;

public abstract class CloseAction extends InvAction {

    public CloseAction() {
        super(ActionType.CLOSE);
    }

    public abstract void action(InventoryCloseEvent event);
}
