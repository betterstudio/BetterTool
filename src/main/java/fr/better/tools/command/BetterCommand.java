package fr.better.tools.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class BetterCommand implements CommandExecutor {

    public interface Parameter{

        default String utility(){ return "don't have any utility defined."; }
        default String parameter(){ return "don't have any parameter defined."; }
        default int parameterSize(){ return parameter().split(" ").length; }
    }

    public interface MachineParameter extends Parameter {
        void action(List<String> args);
    }

    public interface PlayerParameter extends Parameter {
        void action(Player player, List<String> args);
        default String permission(){ return ""; }
    }

    public interface MixParameter extends Parameter {
        default String permission(){ return ""; }
        void action(List<String> args);
        void action(Player player, List<String> args);
    }
}
