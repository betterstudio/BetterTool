package fr.better.tools.listener;

import fr.better.tools.event.PlayerKillEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class KillListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        try{
            Player player = (Player) event.getDamager();
            Player target = (Player) event.getEntity();

            if(target.getHealth() - event.getFinalDamage() <= 0) {
                PlayerKillEvent ev = new PlayerKillEvent(player, target);
                Bukkit.getPluginManager().callEvent(ev);
                if (ev.isCancelled())event.setCancelled(true);
            }
        }catch (Exception e){}
    }
}
