package fr.better.tools.inventory.action;

import fr.better.tools.inventory.action.GAction;
import org.bukkit.event.inventory.InventoryCloseEvent;

public abstract class CloseAction extends GAction {

    public CloseAction() {
        super(Type.CLOSE);
    }

    public abstract void action(InventoryCloseEvent event);
}
