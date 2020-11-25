package fr.better.tools;


import fr.better.tools.command.AdvancedCommand;
import fr.better.tools.command.SimpleCommand;
import fr.better.tools.config.BetterConfig;
import fr.better.tools.config.Config;
import fr.better.tools.deprecated.BListener;
import fr.better.tools.deprecated.Instantiaters;
import fr.better.tools.inventory.shop.DataGui;
import fr.better.tools.inventory.shop.MoneyProvider;
import fr.better.tools.utils.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class BetterPlugin extends JavaPlugin {

    private AdvancedCommand command;
    private BetterConfig config;

    private File configFile;

    ///INITIATERS
    @Override
    public void onEnable() {
        Instantiaters.setPlugin(this);
        getServer().getPluginManager().registerEvents(new BListener(), this);
        onStart();
    }

    @Override
    public void onDisable() {
        onStop();
    }

    ///ABSTRACT
    public abstract void onStart();
    public abstract void onStop();

    public String whoAreYou(){ return ""; }

    ///FUNCTIONS

    ///////COMMANDS
    public ArgumentCreator addArguments(String arguments){
        return new ArgumentCreator(arguments, command);
    }

    public void initCommandExecutor(String name){
        command = new AdvancedCommand(name, this);
    }

    public ArgumentCreator addCommand(String arguments){
        return new ArgumentCreator(arguments, new SimpleCommand(arguments, this));
    }

    public AdvancedCommand getCommand(){ return command; }

    ///////CONFIG
    public Config getBetterConfig(){
        return config;
    }

    public void loadBetterConfig(){
        config = new BetterConfig( "config");
    }

    ////////SETUP
    public void setupMoney(MoneyProvider manager){
        Instantiaters.setManager(manager);
    }

    public void setupShop(){
        DataGui.setup();
    }

    /////////HELP TOOL
    public void listen(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public void suicide() {
        System.out.println("Suiciding plugin.");
        Bukkit.getPluginManager().disablePlugin(this);
    }

    public void shutdown() {
        Bukkit.shutdown();
    }

    public static void sendActionBarToAllPlayers(String message) {
        sendActionBarToAllPlayers(message, -1);
    }

    public static void sendActionBarToAllPlayers(String message, int duration) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            new ActionBar(p).send(message, duration);
        }
    }
}
