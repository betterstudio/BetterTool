package fr.better.tools;

import fr.better.tools.command.*;
import fr.better.tools.config.BetterConfig;
import fr.better.tools.system.BListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BetterPlugin extends JavaPlugin {

    private BetterConfig config;

    @Override
    public void onEnable() {
        listen(new BListener(this));
        onStart();
    }

    @Override
    public void onDisable() {
        onStop();
    }

    public void onStart(){}
    public void onStop(){}

    public Command createComplexCommand(String name){
        return new AdvancedCommand(name);
    }

    public SimpleCommand.Builder createCommand(String name, Action arg){
        return new SimpleCommand.Builder(name, arg);
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
