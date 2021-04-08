package fr.better.tools.inventory.gui.type;

import fr.better.tools.inventory.ICreate;
import fr.better.tools.inventory.gui.GuiCreator;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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

        click(e -> {
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
                        result.accept((Player) e.getWhoClicked(), size);
                    default:
                        return;
                }

                if(size + addValue >= max){
                    size = max;
                    ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.NOTE_BASS, 5, 0);
                }else if(size + addValue <= min){
                    size = min;
                    ((Player) e.getWhoClicked()).playSound(e.getWhoClicked().getLocation(), Sound.NOTE_BASS, 5, 0);
                }else{
                    size = size + addValue;
                    ((Player) e.getWhoClicked()).playNote(e.getWhoClicked().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.A));
                }

                ItemStack valueII = item.apply(size, (size + addValue >= max || size + addValue <= min));
                set(valueII, 13);
                ((Player) e.getWhoClicked()).updateInventory();
        });

        close(e -> {
                result.accept((Player) e.getPlayer(), size);
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

        click(e -> {
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
                        result.accept((Player) e.getWhoClicked(), size);
                    default:
                        return;
                }

                ((Player) e.getWhoClicked()).playNote(e.getWhoClicked().getLocation(),
                        Instrument.PIANO, Note.natural(1, Note.Tone.A));
                size = size + addValue;
                ItemStack valueII = item.apply(size);
                set(valueII, 13);
                ((Player) e.getWhoClicked()).updateInventory();
        });

        close(e -> {
                result.accept((Player) e.getPlayer(), size);
        });
    }
}
