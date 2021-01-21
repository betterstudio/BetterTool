package fr.better.tools.utils.iterator;

import fr.better.tools.utils.AllPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class HashMapWithPlayerKey<T>{

    private final HashMap<UUID, T> data;

    public HashMapWithPlayerKey() {
        this.data = new HashMap<>();
    }
    public HashMapWithPlayerKey(HashMap<UUID, T> data) {
        this.data = data;
    }

    public void replace(Player key, T newValue){
        data.replace(key.getUniqueId(), newValue);
    }

    public void put(Player key, T newValue){
        data.putIfAbsent(key.getUniqueId(), newValue);
    }
    public void put(UUID key, T newValue){
        data.putIfAbsent(key, newValue);
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

    public List<Player> keys(){
        List<Player> players = new ArrayList<>();
        for(UUID u : data.keySet()){

            Player p = Bukkit.getPlayer(u);

            if(p == null){
                data.remove(u);
            }

            players.add(p);
        }
        return players;
    }

    public AllPlayer all(){
        return new AllPlayer(keys());
    }

    public HashMap<UUID, T> toHashMap(){ return data; }
}
