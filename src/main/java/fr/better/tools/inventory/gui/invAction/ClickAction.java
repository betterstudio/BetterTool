package fr.better.tools.inventory.gui.invAction;

import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class ClickAction extends InvAction {

    public ClickAction() {
        super(ActionType.CLICK);
    }

    public abstract void action(InventoryClickEvent event);
}
