package fr.better.tools.command;

import fr.better.tools.command.abstraction.MachineAction;
import fr.better.tools.command.abstraction.MixAction;
import fr.better.tools.command.abstraction.PlayerAction;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class BetterCommand implements CommandExecutor {

    private static String errorArgument = "§7Tu n'as pas fait la bonne commande",
            errorPermission = "§7Tu n'as pas la §3permissions !!",
            topHelpMessage = "§7» §3Commande : !cmd! §7«",
            footHeadMessage = "§7Développé par §3!dev!";

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

    public static class MessageBuilder{
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
        public MessageBuilder setErrorArgument(String message){
            errorArgument = message;
            return this;
        }
    }
}
