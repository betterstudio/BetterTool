package fr.better.tools.command;

import org.bukkit.command.CommandExecutor;

public abstract class BetterCommand implements CommandExecutor {

    protected static String error = "You must be a player to execute this command !";
    protected static String noPermission = "You don't have the permission to execute this command";
    protected static String noArgs = "The arguments don't exist";

    public static void defineSenderErrorMessage(String error){
        BetterCommand.error = error;
    }
    public static void defineNoPermissionMessage(String message){
        BetterCommand.noPermission = message;
    }
    public static void defineNoArgumentMessage(String message){
        BetterCommand.noArgs = message;
    }
}
