package fr.better.tools.inventory.gui.invAction;

public class InvAction {

    private final ActionType type;

    public InvAction(ActionType type) {
        this.type = type;
    }

    public ActionType type() {
        return type;
    }
}
