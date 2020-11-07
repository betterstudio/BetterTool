package fr.better.tools.command.action;

import fr.better.tools.command.action.CAction;
import org.bukkit.entity.Player;

import java.util.List;

public interface PlayerAction extends CAction {

    void executePlayer(Player player, List<String> args);
    default String requirePermission(){
        return "";
    }
}
