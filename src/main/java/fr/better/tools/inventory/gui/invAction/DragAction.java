package fr.better.tools.inventory.gui.invAction;

import org.bukkit.event.inventory.InventoryDragEvent;

public abstract class DragAction extends InvAction {

    public DragAction() {
        super(ActionType.DRAG);
    }

    public abstract void action(InventoryDragEvent event);

}