package fr.better.tools.inventory.gui;

import fr.better.tools.inventory.gui.invAction.ActionType;
import fr.better.tools.inventory.gui.invAction.InvAction;
import org.bukkit.inventory.Inventory;

public interface Gui {

    Inventory give();
    String name();

    InvAction getAction(ActionType type);
    void setAction(InvAction action);
    boolean hasAction(ActionType type);
}
