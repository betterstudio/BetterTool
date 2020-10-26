package fr.better.tools.inventory.action;

import fr.better.tools.inventory.action.GAction;
import org.bukkit.event.inventory.InventoryDragEvent;

public abstract class DragAction extends GAction {

    public DragAction() {
        super(Type.DRAG);
    }

    public abstract void action(InventoryDragEvent event);

}