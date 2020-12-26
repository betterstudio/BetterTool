package fr.better.tools.command;

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
            command).register(argument, (BetterCommand.PlayerParameter)parameter);
        }else{
            ((SimpleCommand)command).setParam((BetterCommand.PlayerAction)parameter);
        }
    }

    public void setupMachine(B parameter){
        if(command instanceof AdvancedCommand){
            AdvancedCommand c = (AdvancedCommand)command;
            c.register(argument, (BetterCommand.MachineParameter)parameter);
        }else{
            ((SimpleCommand)command).setParam((BetterCommand.MachineAction)parameter);
        }
    }

    public void setupMix(C parameter){
        if(command instanceof AdvancedCommand){
            AdvancedCommand c = (AdvancedCommand)command;
            c.register(argument, (BetterCommand.MixParameter)parameter);
        }else{
            ((SimpleCommand)command).setParam((BetterCommand.MixAction) parameter);
        }
    }
}
