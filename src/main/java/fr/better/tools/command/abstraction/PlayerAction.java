package fr.better.tools.command.abstraction;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class PlayerAction implements Action{
    public abstract String action(Player player, List<String> args);
    public String permission(){ return ""; }
}