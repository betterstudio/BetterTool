package fr.better.tools;


import fr.better.tools.command.AdvancedCommand;
import fr.better.tools.command.Parameter;
import fr.better.tools.command.SimpleCommand;
import fr.better.tools.config.BetterConfig;
import fr.better.tools.deprecated.BListener;
import fr.better.tools.deprecated.Instantiaters;
import fr.better.tools.exception.CommandNotFoundException;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BetterPlugin extends JavaPlugin {

    private AdvancedCommand command;
    private BetterConfig config;

    ///INITERS
    @Override
    public void onEnable() {
        Instantiaters.setPlugin(this);
        getServer().getPluginManager().registerEvents(new BListener(), this);
        config = new BetterConfig();
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
        try {
            command = new AdvancedCommand(name, this);
        } catch (CommandNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BetterConfig getBetterConfig(){
        return config;
    }

    public void listen(Listener listener){
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public AdvancedCommand createCommandExecutor(String name){
        AdvancedCommand cmd = null;
        try {
            cmd = new AdvancedCommand(name, this);
        } catch (CommandNotFoundException e) {
            e.printStackTrace();
        }
        return cmd;
    }

    public void addCommand(Parameter parameter){

        new SimpleCommand(parameter, this);
    }

    public void suicide() {
        System.out.println("Suiciding plugin.");
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
