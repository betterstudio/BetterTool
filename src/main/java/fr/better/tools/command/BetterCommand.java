package fr.better.tools.command;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.security.MessageDigest;
import java.util.List;

public abstract class BetterCommand implements CommandExecutor {

    private static String errorArgument = "§7Tu n'as pas fait la bonne commande",
            errorPermission = "§7Tu n'as pas la §3permissions !!",
            topHelpMessage = "§7» §3Commande : !cmd! §7«",
            footHeadMessage = "§7Développez par §3!dev!"
                    ;

    public interface Parameter{
        default String utility(){ return "don't have any utility defined."; }
        default String parameter(){ return "don't have any parameter defined."; }
        default int parameterSize(){ return parameter().split(" ").length; }
    }

    public interface Action{}

    public abstract class MachineAction implements Action{
        public abstract void action(List<String> args);
    }

    public abstract class PlayerAction implements Action{
        public abstract void action(Player player, List<String> args);
        public String permission(){ return ""; }
    }

    public abstract class MixAction implements Action{
        public String permission(){ return ""; }
        public abstract void action(List<String> args);
        public abstract void action(Player player, List<String> args);
    }

    public abstract class MachineParameter extends MachineAction implements Parameter{ }

    public abstract class PlayerParameter extends PlayerAction implements Parameter{}

    public abstract class MixParameter extends MixAction implements Parameter{ }

    public static String getErrorArgument() {
        return errorArgument;
    }

    public static String getErrorPermission() {
        return errorPermission;
    }

    public static String getTopHelpMessage() {
        return topHelpMessage;
    }

    public static String getFootHeadMessage() {
        return footHeadMessage;
    }

    public class MessageBuilder{
        public MessageBuilder setErrorPermission(String message){
            errorPermission = message;
            return this;
        }
        public MessageBuilder setFooterHelp(String message){
            footHeadMessage = message;
            return this;
        }
        public MessageBuilder setHeadHelp(String message){
            topHelpMessage = message;
            return this;
        }
        public MessageBuilder fez(String message){
            errorPermission = message;
            return this;
        }
        public MessageBuilder r(String message){
            errorPermission = message;
            return this;
        }
    }
}
