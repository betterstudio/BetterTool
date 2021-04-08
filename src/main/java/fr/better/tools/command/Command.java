package fr.better.tools.command;

import org.bukkit.command.CommandSender;

public interface Command {

    AdvancedCommand.Builder add(String name, Argument arg);
    void senHelpMessage(CommandSender sender);
}
