package fr.better.tools.config;

import fr.better.tools.deprecated.Instantiaters;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BetterConfig extends YamlConfiguration implements Config{

    private File configFile;
    private List<Change> grammar;

    public BetterConfig(String file) {

        if(!file.contains("."))file = file + ".yml";

        this.configFile = new File(Instantiaters.getPlugin().getDataFolder() + "/" + file);

        if(!configFile.exists())saveConfigToFolder();
        reload();
    }

    public void reload(){
        try {
            load(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void addGrammar(Change... change){
        if(grammar == null)grammar = new ArrayList<>();
        for(Change c : change){
            grammar.add(c);
        }
    }

    public void saveConfigToFolder(){
        Instantiaters.getPlugin().saveResource(configFile.getName(), false);
    }

    @Override
    public Location getLocation(String where) {
        return getLocation(where, null);
    }

    @Override
    public Location getLocation(String where, Location lDefault) {
        try{
            World w = Bukkit.getWorld(getString(where + ".world"));
            int x = getInt(where + ".x");
            int y = getInt(where + ".y");
            int z = getInt(where + ".z");
            return new Location(w, x, y, z);
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public void save(){
        try {
            save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveLocation(String where, Location loc) {
        set(where + ".world", loc.getWorld().getName());
        set(where + ".x", loc.getBlockX());
        set(where + ".y", loc.getBlockY());
        set(where + ".z", loc.getBlockZ());
    }

    @Override
    public Material getMaterial(String where) {
        return getMaterial(where, null);
    }

    @Override
    public Material getMaterial(String where, Material mDefault) {
        Material m = Material.getMaterial(getString(where));
        if(m == null)m = mDefault;
        return m;
    }

    @Override
    public String getMessage(String where) {
        return getMessage(where, false,(String) null, new Change("", ""));
    }

    @Override
    public String getMessage(String where, boolean wantGrammar) {
        return getMessage(where, wantGrammar,(String) null, new Change("", ""));
    }

    @Override
    public String getMessage(String where, String mDefault) {
        return getMessage(where, false, mDefault, new Change("", ""));
    }

    @Override
    public String getMessage(String where, Change... variables) {
        return getMessage(where, false,null, variables);
    }

    @Override
    public String getMessage(String where, boolean wantGrammar, Change... variables) {
        return getMessage(where, wantGrammar, null, variables);
    }

    @Override
    public String getMessage(String where, String mDefault, Change... variables) {
        return getMessage(where, false, mDefault, variables);
    }

    @Override
    public String getMessage(String where, boolean wantGrammar, String mDefault) {
        return getMessage(where, wantGrammar, mDefault, new Change("", ""));
    }

    @Override
    public String getMessage(String where, boolean wantGrammar, String mDefault, Change... variables) {
        String message = getString(where, mDefault).replace("&", "§");
        for(Change config : variables){
            message = message.replace(config.getWord(), config.getReplaced());
        }

        if(this.grammar != null){
            for(Change grammar : this.grammar){
                message = message.replace(grammar.getWord(), grammar.getReplaced());
            }
        }

        if(wantGrammar){
            for(GrammarConfig gc : GrammarConfig.values()){
                message = gc.replace(message);
            }
        }

        return message;
    }
}
