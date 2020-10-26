package fr.better.tools.inventory.action;

import fr.better.tools.inventory.action.GAction;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class ClickAction extends GAction {

    public ClickAction() {
        super(Type.CLICK);
    }

    public abstract void action(InventoryClickEvent event);
}
