package fr.better.tools.command.base;

import fr.better.tools.command.content.Argument;
import org.bukkit.command.CommandSender;

public interface Command {

    void add(String name, Argument arg, ParticularityType type);
    void add(String name, Argument arg);
    void senHelpMessage(CommandSender sender);
}
