package fr.better.tools.utils.iterator;

import fr.better.tools.utils.AllPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerList {

    private final List<UUID> data;

    public PlayerList() {
        this.data = new ArrayList<>();
    }

    public void add(Player key){
        data.add(key.getUniqueId());
    }

    public void remove(Player key){
        data.remove(key);
    }

    public int posOf(Player key){
        return data.indexOf(key.getUniqueId());
    }

    public boolean exist(Player key){
        return data.contains(key.getUniqueId());
    }

    public AllPlayer all(){ return new AllPlayer(toPlayers()); }

    public List<Player> toPlayers(){
        List<Player> players = new ArrayList<>();
        for(UUID u : data){

            Player p = Bukkit.getPlayer(u);

            if(p == null){
                data.remove(u);
            }

            players.add(p);
        }
        return players;
    }

    public List<UUID> toUUIDs() {
        return data;
    }
}
