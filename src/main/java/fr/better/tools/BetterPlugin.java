package fr.better.tools;

import fr.better.tools.command.*;
import fr.better.tools.command.abstraction.*;
import fr.better.tools.config.BetterConfig;
import fr.better.tools.system.BListener;
import fr.better.tools.system.Instantiaters;
import fr.better.tools.utils.Bungeecord;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class BetterPlugin extends JavaPlugin {

    private AdvancedCommand command;
    private BetterConfig config;

    private File configFile;
    private Bungeecord bungee;

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

    ///FUNCTIONS

    ///////COMMANDS
    public ArgumentCreator<PlayerParameter, MachineParameter, MixParameter> addArguments(String arguments){
        return new ArgumentCreator(arguments, command);
    }

    public void initCommandExecutor(String name){
        command = new AdvancedCommand(name, this);
    }

    public ArgumentCreator<PlayerAction, MachineAction, MixAction> addCommand(String arguments){

        return new ArgumentCreator(arguments, new SimpleCommand(arguments, this));
    }

    public AdvancedCommand getCommand(){
        return command;
    }

    ///////CONFIG
    public BetterConfig getBetterConfig(){
        return config;
    }

    public void loadBetterConfig(){
        config = new BetterConfig( "config");
    }

    ////////SETUP

    public BetterCommand.MessageBuilder setupMessageCommand() { return new BetterCommand.MessageBuilder(); }

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
