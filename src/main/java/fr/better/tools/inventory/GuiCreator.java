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

    @Override
    public Inventory give() {
        return inventory;
    }

    @Override
    public String name() {
        return inventory.getTitle();
    }

    @Override
    public GAction getTheAction(GAction.Type type) {
        System.out.println(action);
        for(GAction c : action){
            System.out.println(c.getClass());
            if(c.type() == type)return c;
        }
        return null;
    }


    @Override
    public void setAnAction(GAction action) {
        System.out.println(action);
        if(hasAction(action.type())){
            this.action.removeIf(act -> act.getClass() == action.getClass());
        }
        this.action.add(action);
    }

    @Override
    public boolean hasAction(GAction.Type type) {
        return false;
    }
}
