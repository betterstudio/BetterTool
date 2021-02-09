package fr.better.tools.inventory.gui.type;

import fr.better.tools.inventory.ICreate;
import fr.better.tools.inventory.gui.GuiCreator;
import fr.better.tools.inventory.gui.invAction.ClickAction;
import fr.better.tools.inventory.gui.invAction.CloseAction;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class CalculGui extends GuiCreator {

    public interface CAction{
        void accept(int size, Player player);
    }

    private int size;

    public CalculGui(String name, Material value, int first_jump, int second_jump, int default_value, CAction action, String... lore) {
        super(name, 3);
        setBackLine(7);
        setTopLine(9);
        setSideWays(9);

        size = default_value;
        ItemStack valueI = new ICreate(value).setName("§7Choisir").setLore(
                " ",
                " §fValeur : " + default_value,
                " ",
                " §7§aCliquer pour valider"
        ).build();


        set(new ICreate(Material.SIGN).setName("§c- " + second_jump).build(), 10);
        set(new ICreate(Material.SIGN).setName("§c- " + first_jump).build(), 11);
        set(valueI, 13);
        set(new ICreate(Material.SIGN).setName("§a+ " + first_jump).build(), 15);
        set(new ICreate(Material.SIGN).setName("§a+ " + second_jump).build(), 16);

        setAction(new ClickAction() {
            @Override
            public void action(InventoryClickEvent event) {
                int slot = event.getSlot();
                int addValue = 0;
                event.setCancelled(true);
                switch (slot) {
                    case 10:
                        addValue = -second_jump;
                        break;

                    case 11:
                        addValue = -first_jump;
                        break;

                    case 15:
                        addValue = first_jump;
                        break;

                    case 16:
                        addValue = second_jump;
                        break;

                    case 13:
                        action.accept(size, (Player) event.getWhoClicked());
                    default:
                        return;
                }

                size = size + addValue;
                ItemStack valueII = new ICreate(value).setLore(" ", " §fValeur : " + size).build();
                set(valueII, 12);
                ((Player) event.getWhoClicked()).updateInventory();
            }
        });

        setAction(new CloseAction() {
            @Override
            public void action(InventoryCloseEvent event) {
                action.accept(size, (Player) event.getPlayer());
            }
        });
    }
}
