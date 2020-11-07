package fr.better.tools.config;

import org.bukkit.Location;
import org.bukkit.Material;

public interface Config {

    Location getLocation(String where);
    Location getLocation(String where, Location lDefault);

    void saveLocation(String where, Location loc);

    Material getMaterial(String where);
    Material getMaterial(String where, Material mDefault);

    String getMessage(String where);

    String getMessage(String where, boolean wantGrammar);
    String getMessage(String where, String mDefault);
    String getMessage(String where, VariableConfig... variables);

    String getMessage(String where, boolean wantGrammar, VariableConfig... variables);
    String getMessage(String where, String mDefault, VariableConfig... variables);
    String getMessage(String where, boolean wantGrammar, String mDefault);

    String getMessage(String where, boolean wantGrammar, String mDefault, VariableConfig... variables);

    void save();
}
