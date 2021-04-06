package fr.better.tools.command.abstraction;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class MixAction implements Action{
    public String permission(){ return ""; }
    public abstract String action(List<String> args);
    public abstract String action(Player player, List<String> args);
}