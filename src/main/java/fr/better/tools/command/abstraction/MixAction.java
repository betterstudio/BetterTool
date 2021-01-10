package fr.better.tools.command.abstraction;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class MixAction implements Action{
    public String permission(){ return ""; }
    public abstract void action(List<String> args);
    public abstract void action(Player player, List<String> args);
}