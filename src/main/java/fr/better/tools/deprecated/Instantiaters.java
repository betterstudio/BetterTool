package fr.better.tools.deprecated;

import fr.better.tools.BetterPlugin;
import fr.better.tools.inventory.GuiCreator;
import fr.better.tools.inventory.shop.MoneyProvider;
import fr.better.tools.inventory.shop.ShopManager;

public class Instantiaters {

    private static BetterPlugin plugin;
    private static BListener listener;
    private static MoneyProvider provider;

    public static BetterPlugin getPlugin() {
        return plugin;
    }
    public static void setPlugin(BetterPlugin plugin) {
        Instantiaters.plugin = plugin;
    }

    public static void systemRegisterGui(GuiCreator guiCreator) {
        listener.systemRegisterGui(guiCreator);
    }
    public static void setListener(BListener bListener) {
        listener = bListener;
    }

    public static MoneyProvider getManager() {
        return provider;
    }

    public static void setManager(MoneyProvider manager) {
        Instantiaters.provider = manager;
    }

}
