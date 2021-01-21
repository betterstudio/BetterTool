package fr.better.tools.visual;

import fr.better.tools.system.Instantiaters;
import fr.better.tools.utils.NMS;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Method;

public class ActionBar extends NMS {

    private final String nmsver;
    private final boolean useOldMethods;
    private final Player player;

    public ActionBar(Player player) {
        this.player = player;
        this.nmsver = Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf(".") + 1);

        if (nmsver.equalsIgnoreCase("v1_8_R1") || nmsver.startsWith("v1_7_")) { // Not sure if 1_7 works for the protocol hack?
            this.useOldMethods = true;
        }else{
            this.useOldMethods = false;
        }
    }

    public void send(String message) {
        if (!player.isOnline()) {
            return;
        }

        try {

            Class<?> craftPlayerClass = getBukkitNMSClass("entity.CraftPlayer");
            Object packet;
            Class<?> packetPlayOutChatClass = getMinecraftNMSClass("PacketPlayOutChat");
            if (useOldMethods) {
                Class<?> chatSerializerClass = getMinecraftNMSClass("ChatSerializer");
                Class<?> iChatBaseComponentClass = getMinecraftNMSClass("IChatBaseComponent");
                Method m3 = chatSerializerClass.getDeclaredMethod("a", String.class);
                packet = packetPlayOutChatClass.getConstructor(new Class<?>[]{iChatBaseComponentClass, byte.class}).newInstance(iChatBaseComponentClass.cast(m3.invoke(chatSerializerClass, "{\"text\": \"" + message + "\"}")), (byte) 2);
            } else {
                Class<?> chatComponentTextClass = Class.forName("net.minecraft.server." + nmsver + ".ChatComponentText");
                Class<?> iChatBaseComponentClass = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
                try {
                    Class<?> chatMessageTypeClass = Class.forName("net.minecraft.server." + nmsver + ".ChatMessageType");
                    Object chatMessageType = null;
                    for (Object obj : chatMessageTypeClass.getEnumConstants()) {
                        if (obj.toString().equals("GAME_INFO")) {
                            chatMessageType = obj;
                        }
                    }
                    packet = packetPlayOutChatClass.getConstructor(new Class<?>[]{iChatBaseComponentClass, chatMessageTypeClass}).newInstance(chatComponentTextClass.getConstructor(new Class<?>[]{String.class}).newInstance(message), chatMessageType);
                } catch (ClassNotFoundException cnfe) {
                    packet = packetPlayOutChatClass.getConstructor(new Class<?>[]{iChatBaseComponentClass, byte.class}).newInstance(chatComponentTextClass.getConstructor(new Class<?>[]{String.class}).newInstance(message), (byte) 2);
                }
            }
            Object craftPlayerHandle = craftPlayerClass.getDeclaredMethod("getHandle").invoke(craftPlayerClass.cast(player));

            Object playerConnection = craftPlayerHandle.getClass().getDeclaredField("playerConnection").get(craftPlayerHandle);
            playerConnection.getClass().getDeclaredMethod("sendPacket", getMinecraftNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(final String message, int duration) {
        send(message);

        if (duration >= 0) {
            // Sends empty message at the end of the duration. Allows messages shorter than 3 seconds, ensures precision.
            new BukkitRunnable() {
                @Override
                public void run() {
                    send("");
                }
            }.runTaskLater(Instantiaters.getPlugin(), duration + 1);
        }

        // Re-sends the messages every 3 seconds so it doesn't go away from the player's screen.
        while (duration > 40) {
            duration -= 40;
            new BukkitRunnable() {
                @Override
                public void run() {
                    send(message);
                }
            }.runTaskLater(Instantiaters.getPlugin(), (long) duration);
        }
    }
}
