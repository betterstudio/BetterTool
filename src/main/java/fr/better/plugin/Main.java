package fr.better.plugin;

import fr.better.tools.BetterPlugin;
import fr.better.tools.inventory.shop.MoneyProvider;
import org.bukkit.entity.Player;

public class Main extends BetterPlugin {

    private static Main instance;

    @Override
    public void onStart() {
        setupMoney(new MoneyProvider() {
            @Override
            public int get(Player player) {
                return 1000;
            }

            @Override
            public void set(Player player, int money) {
                System.out.println("New money of " + player.getName() + " is " +  money + " €");
            }
        });
        addCommand(new ShopCommand());
    }

    @Override
    public void onStop() {

    }

    public static Main getInstance() {
        return instance;
    }
}
