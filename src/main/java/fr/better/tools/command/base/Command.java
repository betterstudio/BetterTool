package fr.better.tools.command.base;

import fr.better.tools.command.content.Argument;
import org.bukkit.command.CommandSender;

public interface Command {

    AdvancedCommand.Builder add(String name, Argument arg);
    void senHelpMessage(CommandSender sender);
}
