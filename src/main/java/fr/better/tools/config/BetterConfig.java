package fr.better.tools.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

public class BetterConfig extends YamlConfiguration {

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
