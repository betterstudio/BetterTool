package fr.better.tools.command;

import org.bukkit.command.CommandExecutor;


public abstract class BetterCommand implements CommandExecutor {

    private static String errorArgument = "§7Tu n'as pas fait la bonne commande",
            errorPermission = "§7Tu n'as pas la §3permissions !!",
            mainColor = "§6",
            secondColor = "§7",
            parameter = "§7La commande correcte est : " + mainColor + "/!cmd! !param!",
            who = "Qg"
    ;

    public static String getErrorArgument() {
        return errorArgument;
    }

    public static String getErrorPermission() {
        return errorPermission;
    }

    public static String getMainColor() {
            return mainColor;
    }

    public static String getSecondColor() {
        return secondColor;
    }

    protected static String getWho() {
        return who;
    }

    protected static String getErrorParameter() {
        return parameter;
    }

    public static class MessageBuilder{
        public MessageBuilder setErrorPermission(String message){
            errorPermission = message;
            return this;
        }
        public MessageBuilder setErrorArgument(String message){
            errorArgument = message;
            return this;
        }
        public MessageBuilder setErrorParameter(String message){
            parameter = message;
            return this;
        }
        public MessageBuilder setColor(String main, String second){
            mainColor = main;
            secondColor = second;
            return this;
        }
        public MessageBuilder setDevelopers(String message){
            who = message;
            return this;
        }
    }
}
