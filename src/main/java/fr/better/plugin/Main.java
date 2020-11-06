package fr.better.plugin;

import fr.better.tools.BetterPlugin;
import fr.better.tools.command.Parameter;
import fr.better.tools.command.action.CAction;
import fr.better.tools.command.action.PlayerAction;
import org.bukkit.entity.Player;

import java.util.List;

public class Main extends BetterPlugin {

    @Override
    public void onStart() {
        saveDefaultConfig();
        addCommand(new Parameter(){

            @Override
            public String argument() {
                return "test";
            }

            @Override
            public CAction action() {
                return new PlayerAction() {
                    @Override
                    public void executePlayer(Player player, List<String> args) {
                        player.sendMessage(getBetterConfig().getMessage("Message", true));
                    }

                    @Override
                    public String requirePermission() {
                        return "";
                    }
                };
            }
        });
    }

    @Override
    public void onStop() {

    }
}
