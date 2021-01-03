package fr.better.tools.visual;

import fr.better.tools.utils.NMS;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class Title extends NMS {

    private final Player player;

    public Title(Player player) {
        this.player = player;
    }

    public void send(String title, String subtitle, int fadeInTime, int showTime, int fadeOutTime) {
        try {
            Object chatTitle = getMinecraftNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + title + "\"}");
            Constructor<?> titleConstructor = getMinecraftNMSClass("PacketPlayOutTitle").getConstructor(
                    getMinecraftNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getMinecraftNMSClass("IChatBaseComponent"),
                    int.class, int.class, int.class);
            Object packet = titleConstructor.newInstance(
                    getMinecraftNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chatTitle,
                    fadeInTime, showTime, fadeOutTime);

            Object chatsTitle = getMinecraftNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + subtitle + "\"}");
            Constructor<?> timingTitleConstructor = getMinecraftNMSClass("PacketPlayOutTitle").getConstructor(
                    getMinecraftNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getMinecraftNMSClass("IChatBaseComponent"),
                    int.class, int.class, int.class);
            Object timingPacket = timingTitleConstructor.newInstance(
                    getMinecraftNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chatsTitle,
                    fadeInTime, showTime, fadeOutTime);

            sendPacket(player, packet);
            sendPacket(player, timingPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
