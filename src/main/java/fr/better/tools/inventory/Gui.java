package fr.better.tools.inventory;

import fr.better.tools.inventory.action.GAction;
import org.bukkit.inventory.Inventory;

public interface Gui {

    Inventory give();
    String name();

    GAction getTheAction(GAction.Type type);
    void setAnAction(GAction action);
    boolean hasAction(GAction.Type type);
}
