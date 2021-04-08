package fr.better.tools;

import com.google.inject.Singleton;
import fr.better.tools.command.*;
import fr.better.tools.config.BetterConfig;
import fr.better.tools.system.BListener;
import fr.better.tools.system.BetterModule;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@Singleton
public abstract class BetterPlugin extends JavaPlugin {

    private BetterConfig config;

    @Override
    public void onEnable() {
        new BetterModule(this);
        onStart();
    }

    @Override
    public void onDisable() {
        onStop();
    }

    public abstract void onStart();
    public abstract void onStop();

    public Command createComplexCommand(String name, boolean complex){
        return new AdvancedCommand(name, this);
    }

    public SimpleCommand.Builder createCommand(String name, Argument arg){
        return new SimpleCommand.Builder(name, arg, this);
    }

    public BetterConfig getConfig(){
        return config;
    }

    public void loadBetterConfig(){
        config = new BetterConfig( "config");
    }

    public BetterCommand.MessageBuilder setupMessageCommand() { return new BetterCommand.MessageBuilder(); }

    public void listen(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public void suicide() {
        System.out.println("Suiciding plugin.");
        Bukkit.getPluginManager().disablePlugin(this);
    }

    public void async(Runnable runnable){
        Bukkit.getServer().getScheduler().runTaskAsynchronously(this, runnable);
    }

    public void shutdown() {
        Bukkit.shutdown();
    }
}
