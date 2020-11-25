package fr.better.tools.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NMS {

    public void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getMinecraftNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Class<?> getMinecraftNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server."
                    + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Class<?> getBukkitNMSClass(String name) {
        try {
            return Class.forName(
                    "org.bukkit.craftbukkit."
                    +Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] +
                    "."
                    +
                    name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
