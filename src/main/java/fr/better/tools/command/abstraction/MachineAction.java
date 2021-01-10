package fr.better.tools.command.abstraction;
import fr.better.tools.command.BetterCommand;

import java.util.List;

public abstract class MachineAction implements Action {
    public abstract void action(List<String> args);
}