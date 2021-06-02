package fr.better.tools.inventory.gui.type;

import fr.better.tools.inventory.ItemCreate;
import fr.better.tools.inventory.gui.Gui;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CalculGui extends Gui {

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


        set(new ItemCreate(Material.SIGN).setName("§c- " + second_jump).build(), 10);
        set(new ItemCreate(Material.SIGN).setName("§c- " + first_jump).build(), 11);
        set(valueI, 13);
        set(new ItemCreate(Material.SIGN).setName("§a+ " + first_jump).build(), 15);
        set(new ItemCreate(Material.SIGN).setName("§a+ " + second_jump).build(), 16);

        click((e, p) -> {
                int slot = e.getSlot();
                int addValue = 0;
                e.setCancelled(true);
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
                        result.accept(p, size);
                    default:
                        return;
                }

                if(size + addValue >= max){
                    size = max;
                    p.playSound(p.getLocation(), Sound.NOTE_BASS, 5, 0);
                }else if(size + addValue <= min){
                    size = min;
                    p.playSound(e.getWhoClicked().getLocation(), Sound.NOTE_BASS, 5, 0);
                }else{
                    size = size + addValue;
                    p.playNote(e.getWhoClicked().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.A));
                }

                ItemStack valueII = item.apply(size, (size + addValue >= max || size + addValue <= min));
                set(valueII, 13);
                p.updateInventory();
        });

        close((e,p) -> {
                result.accept(p, size);
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

        set(new ItemCreate(Material.SIGN).setName("§c- " + second_jump).build(), 10);
        set(new ItemCreate(Material.SIGN).setName("§c- " + first_jump).build(), 11);
        set(valueI, 13);
        set(new ItemCreate(Material.SIGN).setName("§a+ " + first_jump).build(), 15);
        set(new ItemCreate(Material.SIGN).setName("§a+ " + second_jump).build(), 16);

        click((event, player) -> {
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
                        result.accept(player, size);
                    default:
                        return;
                }

                player.playNote(player.getLocation(),
                        Instrument.PIANO, Note.natural(1, Note.Tone.A));
                size = size + addValue;
                ItemStack valueII = item.apply(size);
                set(valueII, 13);
                player.updateInventory();
        });

        close((event, player) -> {
                result.accept(player, size);
        });
    }
}
