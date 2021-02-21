package fr.better.tools;

import fr.better.tools.system.Instantiaters;
import fr.better.tools.utils.NMS;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

public class BetterBukkit {

    public static void sendActionBarToAllPlayers(String message) {
        sendActionBarToAllPlayers(message, -1);
    }

    public static void sendActionBarToAllPlayers(String message, int duration) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            sendActionBar(p, message, duration);
        }
    }

    public static void sendTitleToAllPlayers(String title, String sub){
        for (Player p : Bukkit.getOnlinePlayers()) {
            sendTitle(p, title, sub);
        }
    }

    public static void sendTitle(Player player, String title, String subtitle) {
        try {
            Object chatTitle = NMS.getMinecraftNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + title + "\"}");
            Constructor<?> titleConstructor = NMS.getMinecraftNMSClass("PacketPlayOutTitle").getConstructor(
                    NMS.getMinecraftNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], NMS.getMinecraftNMSClass("IChatBaseComponent"),
                    int.class, int.class, int.class);
            Object packet = titleConstructor.newInstance(
                    NMS.getMinecraftNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chatTitle,
                    5, 20, 5);

            Object chatsTitle = NMS.getMinecraftNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + subtitle + "\"}");
            Constructor<?> timingTitleConstructor = NMS.getMinecraftNMSClass("PacketPlayOutTitle").getConstructor(
                    NMS.getMinecraftNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], NMS.getMinecraftNMSClass("IChatBaseComponent"),
                    int.class, int.class, int.class);
            Object timingPacket = timingTitleConstructor.newInstance(
                    NMS.getMinecraftNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chatsTitle,
                    5, 20, 5);

            NMS.sendPacket(player, packet);
            NMS.sendPacket(player, timingPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendActionBar(Player player, String message) {
        try {

            Class<?> craftPlayerClass = NMS.getBukkitNMSClass("entity.CraftPlayer");
            Object packet;
            Class<?> packetPlayOutChatClass = NMS.getMinecraftNMSClass("PacketPlayOutChat");
            if (Bukkit.getServer().getClass().getPackage().getName().
                    substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf(".") + 1).equalsIgnoreCase("v1_8_R1")) {
                Class<?> chatSerializerClass = NMS.getMinecraftNMSClass("ChatSerializer");
                Class<?> iChatBaseComponentClass = NMS.getMinecraftNMSClass("IChatBaseComponent");
                Method m3 = chatSerializerClass.getDeclaredMethod("a", String.class);
                packet = packetPlayOutChatClass.getConstructor(new Class<?>[]{iChatBaseComponentClass, byte.class}).newInstance(iChatBaseComponentClass.cast(m3.invoke(chatSerializerClass, "{\"text\": \"" + message + "\"}")), (byte) 2);
            } else {
                Class<?> chatComponentTextClass = NMS.getMinecraftNMSClass("ChatComponentText");
                Class<?> iChatBaseComponentClass = NMS.getMinecraftNMSClass("IChatBaseComponent");

                Class<?> chatMessageTypeClass = NMS.getMinecraftNMSClass("ChatMessageType");
                Object chatMessageType = null;
                for (Object obj : chatMessageTypeClass.getEnumConstants()) {
                    if (obj.toString().equals("GAME_INFO")) {
                        chatMessageType = obj;
                    }
                }
                packet = packetPlayOutChatClass.getConstructor(new Class<?>[]{iChatBaseComponentClass, chatMessageTypeClass}).newInstance(chatComponentTextClass.getConstructor(new Class<?>[]{String.class}).newInstance(message), chatMessageType);
            }
            Object craftPlayerHandle = craftPlayerClass.getDeclaredMethod("getHandle").invoke(craftPlayerClass.cast(player));

            Object playerConnection = craftPlayerHandle.getClass().getDeclaredField("playerConnection").get(craftPlayerHandle);
            playerConnection.getClass().getDeclaredMethod("sendPacket", NMS.getMinecraftNMSClass("Packet")).invoke(playerConnection, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendActionBar(final Player player, final String message, int duration) {
        sendActionBar(player, message);

        if (duration >= 0) {
            // Sends empty message at the end of the duration. Allows messages shorter than 3 seconds, ensures precision.
            new BukkitRunnable() {
                @Override
                public void run() {
                    sendActionBar(player, "");
                }
            }.runTaskLater(Instantiaters.getPlugin(), duration + 1);
        }

        // Re-sends the messages every 3 seconds so it doesn't go away from the player's screen.
        while (duration > 40) {
            duration -= 40;
            new BukkitRunnable() {
                @Override
                public void run() {
                    sendActionBar(player, message);
                }
            }.runTaskLater(Instantiaters.getPlugin(), (long) duration);
        }
    }

    public static void give(Player player, HashMap<Integer, ItemStack> stuff) {
        for(Integer i : stuff.keySet()){
            switch(i){
                case 100:
                    player.getInventory().setBoots(stuff.get(i));
                    break;
                case 101:
                    player.getInventory().setLeggings(stuff.get(i));
                    break;
                case 102:
                    player.getInventory().setChestplate(stuff.get(i));
                    break;
                case 103:
                    player.getInventory().setHelmet(stuff.get(i));
                    break;
                default:
                    player.getInventory().setItem(i, stuff.get(i));
                    break;
            }
        }
    }

    public static void removePotion(Player p) {
        for (PotionEffect effect : p.getActivePotionEffects())
            p.removePotionEffect(effect.getType());
    }

    public static void clear(Player player){
        player.getInventory().setArmorContents(null);
        player.getInventory().clear();
    }
}
