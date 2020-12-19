package fr.better.tools.inventory;

import fr.better.tools.inventory.action.GAction;
import org.bukkit.inventory.Inventory;

public interface Gui {

    Inventory give();
    String name();

    GAction getAction(GAction.Type type);
    void setAction(GAction action);
    boolean hasAction(GAction.Type type);

}
