package fr.better.tools;

import fr.better.tools.command.AdvancedCommand;
import fr.better.tools.command.BetterCommand;
import fr.better.tools.command.SimpleCommand;

public class ArgumentCreator {

    private final String argument;
    private final BetterCommand command;

    public ArgumentCreator(String argument, BetterCommand command) {
        this.argument = argument;
        this.command = command;
    }

    public void setupPlayer(BetterCommand.PlayerParameter parameter){

        if(command instanceof AdvancedCommand){
            ((AdvancedCommand)
            command).register(argument, parameter);
        }else{
            ((SimpleCommand)command).setParam(parameter);
        }
    }

    public void setupMachine(BetterCommand.MachineParameter parameter){
        if(command instanceof AdvancedCommand){
            AdvancedCommand c = (AdvancedCommand)command;
            c.register(argument, parameter);
        }else{
            ((SimpleCommand)command).setParam(parameter);
        }
    }

    public void setupMix(BetterCommand.MixParameter parameter){
        if(command instanceof AdvancedCommand){
            AdvancedCommand c = (AdvancedCommand)command;
            c.register(argument, parameter);
        }else{
            ((SimpleCommand)command).setParam(parameter);
        }
    }

}
