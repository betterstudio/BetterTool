package fr.better.tools.config.serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SLocation implements Serializable {

    private Map<String, Object> serialized;

    public SLocation(Map<String, Object> serialized) {
        this.serialized = serialized;
    }

    public SLocation(Location loc) {
        serialized = loc.serialize();
    }

    public SLocation() {
    }

    public Location to(){
        return Location.deserialize(serialized);
    }

    public void setSerialized(Map<String, Object> serialized) {
        this.serialized = serialized;
    }
}
