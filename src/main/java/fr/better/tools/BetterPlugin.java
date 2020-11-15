package fr.better.tools;


import fr.better.tools.command.AdvancedCommand;
import fr.better.tools.command.BetterCommand;
import fr.better.tools.command.SimpleCommand;
import fr.better.tools.config.BetterConfig;
import fr.better.tools.config.Config;
import fr.better.tools.deprecated.BListener;
import fr.better.tools.deprecated.Instantiaters;
import fr.better.tools.inventory.shop.DataGui;
import fr.better.tools.inventory.shop.MoneyProvider;
import org.bukkit.Bukkit;
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
        DataGui.setup();
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
    public void addArguments(String arguments, BetterCommand.Parameter parameter){
        if(command == null)return;
        command.register(arguments, parameter);
    }

    public void initCommandExecutor(String name){
        command = new AdvancedCommand(name, this);
    }

    public AdvancedCommand createCommandExecutor(String name){
        AdvancedCommand cmd = null;
        cmd = new AdvancedCommand(name, this);
        return cmd;
    }

    public void addCommand(String arguments, BetterCommand.Parameter parameter){
        new SimpleCommand(arguments, parameter, this);
    }

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
}
