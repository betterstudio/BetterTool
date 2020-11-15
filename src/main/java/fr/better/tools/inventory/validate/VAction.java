package fr.better.tools.inventory.validate;

public interface VAction {

    void doWhenAccept();
    default void doWhenDeny(){}
}
