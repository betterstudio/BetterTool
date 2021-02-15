package fr.better.tools.inventory.gui;

import fr.better.tools.inventory.gui.invAction.ActionType;
import fr.better.tools.inventory.gui.invAction.InvAction;
import fr.better.tools.system.Instantiaters;
import org.bukkit.inventory.Inventory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GuiCreator extends GuiManager implements Gui {

    private final List<InvAction> action;

    public GuiCreator(String name, int line) {
        super(name, line);
        Instantiaters.systemRegisterGui(this);
        action = new ArrayList<>();
    }

    private GuiCreator(Inventory inventory) {
        super(inventory);
        Instantiaters.systemRegisterGui(this);
        action = new ArrayList<>();
    }

    public static GuiCreator asGui(Inventory inv){
        return new GuiCreator(inv);
    }

    @Override
    public Inventory give() {
        return inventory;
    }

    @Override
    public String name() {
        return inventory.getTitle();
    }

    public InvAction getAction(ActionType type) {
        for(InvAction c : action){
            if(c.type() == type)return c;
        }
        return null;
    }

    public void setAction(InvAction action) {
        if(hasAction(action.type())){
            this.action.removeIf(act -> act.getClass() == action.getClass());
        }
        this.action.add(action);
    }

    public boolean hasAction(ActionType type) {
        for(InvAction act : action){
            if(act.type() == type)return true;
        }
        return false;
    }

}
