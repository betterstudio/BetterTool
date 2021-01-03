package fr.better.tools.inventory.gui.validate;

public interface VAction {

    void doWhenAccept();
    default void doWhenDeny(){}
}
