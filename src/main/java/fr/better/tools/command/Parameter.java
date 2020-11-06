package fr.better.tools.command;

import fr.better.tools.command.action.MachineAction;
import fr.better.tools.command.action.PlayerAction;
import fr.better.tools.command.action.CAction;
import fr.better.tools.command.action.MixAction;

public interface Parameter{

    String argument();
    CAction action();

    default String utility(){ return "don't have any utility defined."; }
    default String parameter(){ return "don't have any parameter defined."; }
    default int parameterSize(){ return parameter().split(" ").length; }

}
