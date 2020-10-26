package fr.better.tools.command.action;

import fr.better.tools.command.action.CAction;

import java.util.List;

public interface MachineAction extends CAction {

    void executeMachine(List<String> args);
}
