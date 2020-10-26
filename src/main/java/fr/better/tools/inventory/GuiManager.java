package fr.better.tools.inventory;

import fr.better.tools.utils.ICreate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class GuiManager {

    protected final Inventory inventory;

    public GuiManager(String name, int line){
        this.inventory = Bukkit.createInventory(null, line*9, name);
    }

    public void setTopLine(int data){
        ItemStack item = new ICreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build();
        for(int i = 0; i < 9; i++){
            inventory.setItem(i, item);
        }
    }

    public void setBackLine(int data){
        ItemStack item = new ICreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build();
        for(int i = inventory.getSize()-9; i < inventory.getSize(); i++){
            inventory.setItem(i, item);
        }
    }

    public void setThePerfect(int data){
        try{
            int[] i = {0, 1, 2, 6, 7, 8, 9, 17, 36, 45, 46, 47, 48, 52, 53, 54};
            ItemStack item = new ICreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build();

            for(int in : i){ inventory.setItem(in,  item); }
        }catch(Exception ex){
            System.out.println("You can use that uniquely on a 6 line sized chest !");
        }
    }

    public void setSideWays(int data){
        ItemStack item = new ICreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build();
        switch(inventory.getSize()/9){
            case 6:
                inventory.setItem(45, item);
                inventory.setItem(53, item);
            case 5:
                inventory.setItem(36, item);
                inventory.setItem(44, item);
            case 4:
                inventory.setItem(27, item);
                inventory.setItem(35, item);
            case 3:
                inventory.setItem(18, item);
                inventory.setItem(26, item);
            case 2:
                inventory.setItem(9, item);
                inventory.setItem(17, item);
            case 1:
                inventory.setItem(0, item);
                inventory.setItem(8, item);
                break;
        }
    }

    public GuiManager define(List<Integer> where, ItemStack what){
        for(int i : where){
            ItemStack item = Collections.singletonList(what).get(where.get(i));
            inventory.setItem(i, item);
        }
        return this;
    }

    public GuiManager define(int where, ItemStack item){
        inventory.setItem(where, item);
        return this;
    }
}
