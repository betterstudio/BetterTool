package fr.better.tools;


import fr.better.tools.command.AdvancedCommand;
import fr.better.tools.command.Parameter;
import fr.better.tools.command.SimpleCommand;
import fr.better.tools.config.BetterConfig;
import fr.better.tools.config.Config;
import fr.better.tools.deprecated.BListener;
import fr.better.tools.deprecated.Instantiaters;
import fr.better.tools.exception.CommandNotFoundException;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;

public abstract class BetterPlugin extends JavaPlugin {

    private AdvancedCommand command;
    private BetterConfig config;

    private File configFile;

    ///INITERS
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
    public void addArguments(Parameter parameter){
        if(command == null)return;
        command.register(parameter);
    }

    public void initCommandExecutor(String name){
        command = new AdvancedCommand(name, this);
    }

    public Config getBetterConfig(){
        return config;
    }

    public void loadBetterConfig(){
        config = new BetterConfig( "config");
    }

    public void listen(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public AdvancedCommand createCommandExecutor(String name){
        AdvancedCommand cmd = null;
        cmd = new AdvancedCommand(name, this);
        return cmd;
    }

    public void addCommand(Parameter parameter){ new SimpleCommand(parameter, this); }

    public void suicide() {
        System.out.println("Suiciding plugin.");
        Bukkit.getPluginManager().disablePlugin(this);
    }

    public void shutdown() {
        Bukkit.shutdown();
    }
}
