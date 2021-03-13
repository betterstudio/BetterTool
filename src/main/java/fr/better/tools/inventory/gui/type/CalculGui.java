package fr.better.tools.inventory.gui.type;

import fr.better.tools.inventory.ICreate;
import fr.better.tools.inventory.gui.GuiCreator;
import fr.better.tools.inventory.gui.invAction.ClickAction;
import fr.better.tools.inventory.gui.invAction.CloseAction;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class CalculGui extends GuiCreator {

    private int size;

    public CalculGui(String name,
                     int first_jump,
                     int second_jump,
                     int default_value,
                     int min,
                     int max,
                     BiConsumer<Player, Integer> result,
                     BiFunction<Integer, Boolean, ItemStack> item,
                     int colorGlass) {

        super(name, 3);
        setBackLine(colorGlass);
        setTopLine(colorGlass);
        setSideWays(colorGlass);


        size = default_value;
        ItemStack valueI = item.apply(default_value, false);


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
                        result.accept((Player) event.getWhoClicked(), size);
                    default:
                        return;
                }

                if(size + addValue >= max){
                    size = max;
                    ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_BASS, 5, 0);
                }else if(size + addValue <= min){
                    size = min;
                    ((Player) event.getWhoClicked()).playSound(event.getWhoClicked().getLocation(), Sound.NOTE_BASS, 5, 0);
                }else{
                    size = size + addValue;
                    ((Player) event.getWhoClicked()).playNote(event.getWhoClicked().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.A));
                }

                ItemStack valueII = item.apply(size, (size + addValue >= max || size + addValue <= min));
                set(valueII, 13);
                ((Player) event.getWhoClicked()).updateInventory();
            }
        });

        setAction(new CloseAction() {
            @Override
            public void action(InventoryCloseEvent event) {
                result.accept((Player) event.getPlayer(), size);
            }
        });
    }

    public CalculGui(String name,
                     int first_jump,
                     int second_jump,
                     int default_value,
                     BiConsumer<Player, Integer> result,
                     Function<Integer, ItemStack> item,
                     int colorGlass) {

        super(name, 3);
        setBackLine(colorGlass);
        setTopLine(colorGlass);
        setSideWays(colorGlass);


        size = default_value;
        ItemStack valueI = item.apply(default_value);


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
                        result.accept((Player) event.getWhoClicked(), size);
                    default:
                        return;
                }

                ((Player) event.getWhoClicked()).playNote(event.getWhoClicked().getLocation(),
                        Instrument.PIANO, Note.natural(1, Note.Tone.A));
                size = size + addValue;
                ItemStack valueII = item.apply(size);
                set(valueII, 13);
                ((Player) event.getWhoClicked()).updateInventory();
            }
        });

        setAction(new CloseAction() {
            @Override
            public void action(InventoryCloseEvent event) {
                result.accept((Player) event.getPlayer(), size);
            }
        });
    }
}
