package fr.better.tools.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class BetterConfig extends YamlConfiguration{

    public BetterConfig(){
        // ILTOTORE STP :D
    }

    public void saveLocation(Location loc, String where){
        set(where + ".world", loc.getWorld().getName());
        set(where + ".x", loc.getBlockX());
        set(where + ".y", loc.getBlockY());
        set(where + ".z", loc.getBlockZ());
    }

    public Location getLocation(String where){
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

    public Location getLocation(String where, Location locationDefault){
        Location loc = getLocation(where);
        if(loc == null)loc = locationDefault;
        return loc;
    }

    public Material getMaterial(String where, Material mat){
        Material m = Material.getMaterial(getString(where));
        if(m == null)m = mat;
        return m;
    }

    public String getMessage(String where, boolean wantGrammar, VariableConfig... variables){
        return getMessage(where, wantGrammar, "Message not found", variables);
    }

    public String getMessage(String where, boolean wantGrammar,  String defaultMessage, VariableConfig... variables){
        String message = getString(where, defaultMessage).replace("&", "§");

        for(VariableConfig config : variables){
            message = message.replace(config.getWord(), config.getReplaced());
        }

        for(GrammarConfig gc : GrammarConfig.values()){
            message = gc.replace(message);
        }

        return message;
    }
}
