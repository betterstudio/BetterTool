package fr.better.tools.inventory;

import fr.better.tools.deprecated.Instantiaters;
import fr.better.tools.inventory.action.GAction;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GuiCreator extends GuiManager implements Gui {

    private final List<GAction> action;

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

    @Override
    public GAction getAction(GAction.Type type) {
        for(GAction c : action){
            if(c.type() == type)return c;
        }
        return null;
    }


    @Override
    public void setAction(GAction action) {
        if(hasAction(action.type())){
            this.action.removeIf(act -> act.getClass() == action.getClass());
        }
        this.action.add(action);
    }

    @Override
    public boolean hasAction(GAction.Type type) {
        for(GAction act : action){
            if(act.type() == type)return true;
        }
        return false;
    }
}
