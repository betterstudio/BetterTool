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

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class PutItemStackGui extends GuiCreator {

    public PutItemStackGui(String name, int line, BiConsumer<Player, List<ItemStack>> action) {
        super(name, line);

        setTopLine(9);
        setBackLine(9);

        ItemStack validate = new ICreate(Material.DARK_OAK_DOOR).setName("§aValider").build();


        setAction(new ClickAction() {
            @Override
            public void action(InventoryClickEvent event) {
                if(event.getRawSlot() == line-1 || event.getRawSlot() == 0){
                    event.setCancelled(true);
                }
                if(event.getCurrentItem() == null)return;
                if(event.getCurrentItem().isSimilar(validate)){
                    int slot = 0;
                    List<ItemStack> items = new ArrayList<>();
                    for(ItemStack stack : inventory.getContents()){
                        if(stack == null)continue;
                        if(slot < 9 && slot > ((line-1)*9)-1)continue;
                        items.add(stack);
                        slot++;
                    }
                    action.accept((Player) event.getWhoClicked(), items);
                }
            }
        });

        setAction(new CloseAction() {
            @Override
            public void action(InventoryCloseEvent event) {

                int slot = 0;
                List<ItemStack> items = new ArrayList<>();
                for(ItemStack stack : inventory.getContents()){
                    if(stack == null)continue;
                    if(slot < 9 && slot > ((line-1)*9)-1)continue;
                    items.add(stack);
                    slot++;
                }
                action.accept((Player) event.getPlayer(), items);
            }
        });
    }
}
