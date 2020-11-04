package fr.better.tools.config.serializable;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SItem {

    private Map<String, Object> serialized;

    public SItem(Map<String, Object> serialized) {
        this.serialized = serialized;
    }

    public SItem(ItemStack item){
        serialized = item.serialize();
    }

    public SItem() {
    }

    public ItemStack to(){
        return ItemStack.deserialize(serialized);
    }

    public void setSerialized(Map<String, Object> serialized) {
        this.serialized = serialized;
    }
}
