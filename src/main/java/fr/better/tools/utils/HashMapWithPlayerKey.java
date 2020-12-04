package fr.better.tools.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class HashMapWithPlayerKey<T>{

    private final HashMap<UUID, T> data;

    public HashMapWithPlayerKey() {
        this.data = new HashMap<>();
    }

    public void replace(Player key, T newValue){
        data.replace(key.getUniqueId(), newValue);
    }

    public void put(Player key, T newValue){
        data.putIfAbsent(key.getUniqueId(), newValue);
    }

    public void remove(Player key){
        data.remove(key);
    }

    public T get(Player key){
        return data.get(key.getUniqueId());
    }

    public boolean exist(Player key){
        return data.containsKey(key.getUniqueId());
    }
}
