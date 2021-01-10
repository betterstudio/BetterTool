package fr.better.tools.command;

import fr.better.tools.command.abstraction.*;

public class ArgumentCreator<A, B, C> {

    private final String argument;
    private final BetterCommand command;

    public ArgumentCreator(String argument, BetterCommand command) {
        this.argument = argument;
        this.command = command;
    }

    public void setupPlayer(A parameter){

        if(command instanceof AdvancedCommand){
            ((AdvancedCommand)
            command).register(argument, (PlayerParameter)parameter);
        }else{
            ((SimpleCommand)command).setParam((PlayerAction)parameter);
        }
    }

    public void setupMachine(B parameter){
        if(command instanceof AdvancedCommand){
            AdvancedCommand c = (AdvancedCommand)command;
            c.register(argument, (MachineParameter)parameter);
        }else{
            ((SimpleCommand)command).setParam((MachineAction)parameter);
        }
    }

    public void setupMix(C parameter){
        if(command instanceof AdvancedCommand){
            AdvancedCommand c = (AdvancedCommand)command;
            c.register(argument, (MixParameter)parameter);
        }else{
            ((SimpleCommand)command).setParam((MixAction) parameter);
        }
    }
}
