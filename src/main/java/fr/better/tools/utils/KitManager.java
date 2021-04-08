package fr.better.tools.utils;

import fr.better.tools.BetterBukkit;
import fr.better.tools.command.AdvancedCommand;
import fr.better.tools.command.Argument;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KitManager {

    private final List<KitHandler> editing;

    public KitManager(AdvancedCommand command, String valideCommandName) {
        this.editing = new ArrayList<>();
        new AdvancedCommand.Builder(command, valideCommandName, new ValidArgument(this));
    }

    public void clear(){
        editing.clear();
    }

    public KitHandler asInstance(Player player, HashMap<Integer, ItemStack> defaultInv, Result result){
        return new KitHandler(player, defaultInv, result, this);
    }

    public interface Result {
        void result(HashMap<Integer, ItemStack> content);
    }

    public class KitHandler {

        private final Player player;
        private final Result result;
        private final HashMap<Integer, ItemStack> oldInventory;

        private final KitManager manager;

        public KitHandler(Player player, HashMap<Integer, ItemStack> defaultInv, Result result, KitManager manager) {

            this.manager = manager;
            this.player = player;
            this.result = result;
            this.oldInventory = new HashMap<>();

            int slot = 0;
            for (ItemStack stack : player.getInventory().getContents()) {

                if (stack == null) {
                    slot++;
                    continue;
                }

                oldInventory.put(slot, stack);
                slot++;
            }

            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.setGameMode(GameMode.CREATIVE);

            BetterBukkit.give(player, defaultInv);
            manager.editing.add(this);
        }

        public void valid() {
            HashMap<Integer, ItemStack> r = new HashMap<>();
            int slot = 0;

            for (ItemStack stack : player.getInventory().getContents()) {

                if (stack == null) {
                    slot++;
                    continue;
                }

                r.put(slot, stack);
                slot++;
            }

            if (player.getInventory().getBoots() != null) r.put(100, player.getInventory().getBoots());
            if (player.getInventory().getLeggings() != null) r.put(101, player.getInventory().getLeggings());
            if (player.getInventory().getChestplate() != null) r.put(102, player.getInventory().getChestplate());
            if (player.getInventory().getHelmet() != null) r.put(103, player.getInventory().getHelmet());

            BetterBukkit.clear(player);

            BetterBukkit.give(player, oldInventory);
            player.setGameMode(GameMode.SURVIVAL);
            result.result(r);

            manager.editing.remove(this);
        }

        public Player getPlayer() {
            return player;
        }

        public Result getResult() {
            return result;
        }

        public HashMap<Integer, ItemStack> getOldInventory() {
            return oldInventory;
        }
    }

    public class ValidArgument extends Argument {

        private final KitManager kitManager;

        public ValidArgument(KitManager kitManager) {
            this.kitManager = kitManager;
        }

        @Override
        public String execute(Player player, List<String> args) {
            for(KitHandler handler : editing){
                if(handler.getPlayer() == player){
                    handler.valid();
                    return "§7Tu as validé le nouveau kit !";
                }
            }
            return "§7Tu n'es pas en mode edition de kit !";
        }

        @Override
        public String utility() {
            return "valide l'inventaire actuel";
        }

        @Override
        public String parameter() {
            return "";
        }
    }

}
