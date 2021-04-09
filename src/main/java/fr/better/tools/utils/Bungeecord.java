package fr.better.tools.utils;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.better.tools.system.BListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Bungeecord implements PluginMessageListener {

    private interface Result{
        void receive(ByteArrayDataInput input);
    }

    public class Message{

        private final Player player;
        private final String channel;
        private final String subChannel;
        private final List<String> param;
        private Consumer<ByteArrayDataInput> result;

        public Message(Player player, String channel, String subChannel, String... param) {
            this.player = player;
            this.channel = channel;

            if(!Bukkit.getMessenger().isIncomingChannelRegistered(BListener.MAIN, channel))Bukkit.getMessenger().registerIncomingPluginChannel(BListener.MAIN, channel, new Bungeecord());
            if(!Bukkit.getMessenger().isOutgoingChannelRegistered(BListener.MAIN, channel))Bukkit.getMessenger().registerOutgoingPluginChannel(BListener.MAIN, channel);

            this.subChannel = subChannel;
            this.param = Arrays.asList(param);
        }

        public Message asResult(Consumer<ByteArrayDataInput> result){
            this.result = result;
            return this;
        }

        public void send(){
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF(subChannel);
            param.forEach((text) -> { out.writeUTF(text); });
            player.sendPluginMessage(BListener.MAIN, channel, out.toByteArray());

            if(result != null)messages.add(this);
        }
    }

    private final List<Message> messages = new ArrayList<>();

    public int getPlayerCount(Player p){
        final int[] size = {0};
        new Message(p, "BungeeCord", "PlayerCount", "ALL").asResult((r) -> {
              size[0] = Integer.parseInt(r.readUTF());
        });
        return size[0];
    }

    @Deprecated
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        for(Message result : messages){

            if(!channel.equalsIgnoreCase(result.channel))continue;

            ByteArrayDataInput in = ByteStreams.newDataInput(message);
            String subchannel = in.readUTF();

            if(!subchannel.equalsIgnoreCase(result.subChannel))continue;
            if(result.player != player)continue;

            result.result.accept(in);
        }
    }
}
