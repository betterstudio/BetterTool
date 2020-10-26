package fr.better.tools.inventory.action;

public abstract class GAction {

    private final Type type;

    public enum Type {
        CLICK, CLOSE, DRAG;
    }

    public GAction(Type type){
        this.type = type;
    }

    public Type type() {
        return type;
    }
}
