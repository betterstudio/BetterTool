package fr.better.plugin;

import fr.better.tools.command.Parameter;
import fr.better.tools.command.action.CAction;
import fr.better.tools.command.action.PlayerAction;
import fr.better.tools.inventory.shop.ShopManager;
import fr.better.tools.utils.ICreate;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class ShopCommand implements Parameter {

    @Override
    public String argument() {
        return "shop";
    }

    @Override
    public CAction action() {
        return new PlayerAction() {
            @Override
            public void executePlayer(Player player, List<String> args) {
                ShopManager shop = new ShopManager("§3Shop §8§o#menu", 1);
                shop.addItem().setBuyPrice(50).setSellPrice(30)
                        .setViewItems(new ICreate(Material.APPLE).build())
                        .setContentItem(new ICreate(Material.APPLE).build())
                        .setSlot(4).build();
               player.openInventory(shop.give());
            }
        };
    }
}
