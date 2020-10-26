package fr.better.tools.command;

import fr.better.tools.command.action.MachineAction;
import fr.better.tools.command.action.PlayerAction;
import fr.better.tools.command.action.CAction;
import fr.better.tools.command.action.MixAction;

public class Parameter{

    private String arguments;
    private CAction action;

    public Parameter(String args, PlayerAction action){
        this.arguments = args;
        this.action = action;
    }

    public Parameter(String args, MixAction action){
        this.arguments = args;
        this.action = action;
    }

    public Parameter(String args, MachineAction action){
        this.arguments = args;
        this.action = action;
    }

    public String utility(){
        return "don't have any utility defined.";
    }

    public String parameter(){
        return "don't have any parameter defined.";
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public CAction getAction() {
        return action;
    }

    public int getParameterSize() {
        return parameter().split(" ").length;
    }

}
