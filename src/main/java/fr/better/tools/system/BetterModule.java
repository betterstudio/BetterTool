package fr.better.tools.system;

import com.google.inject.AbstractModule;
import fr.better.tools.BetterPlugin;
import org.bukkit.Bukkit;

public class BetterModule extends AbstractModule {

    private BetterPlugin plugin;

    public BetterModule(BetterPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        this.bind(BListener.class).toInstance(new BListener(plugin));
        this.bind(BetterPlugin.class).toInstance(plugin);
    }
}
