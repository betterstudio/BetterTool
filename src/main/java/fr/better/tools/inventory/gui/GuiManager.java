package fr.better.tools.inventory.gui;

import fr.better.tools.inventory.ICreate;
import fr.better.tools.inventory.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiManager extends InventoryUtils {

    public GuiManager(String name, int line){
        super(Bukkit.createInventory(null, line*9, name));
    }

    public GuiManager(Inventory inventory){
        super(inventory);
    }

    public void setTopLine(int data){
        setTopLine(new ICreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build());
    }

    public void setTopLine(ItemStack item){
       for(int i = 0; i < 9; i++){
            inventory.setItem(i, item);
        }
    }

    public void setBackLine(int data){
        setBackLine(new ICreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build());
    }

    public void setBackLine(ItemStack item){
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

    public void setFull(ItemStack what){
        for(int i = 0; i < inventory.getSize(); i++){
            inventory.setItem(i, what);
        }
    }

    public void setFull(int color){
        for(int i = 0; i < inventory.getSize(); i++){
            inventory.setItem(i,  new ICreate(Material.STAINED_GLASS_PANE, 1, (short) color).setName(" ").build());
        }
    }

    public void setSideWays(ItemStack item){
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

    public void setSideWays(int data){
        setSideWays(new ICreate(Material.STAINED_GLASS_PANE, 1, (short) data).setName(" ").build());
    }

    public void set(ItemStack what, int... where){
        for(int i : where){
            inventory.setItem(i, what);
        }
    }

    public void add(ItemStack... item){
        for(ItemStack i : item)inventory.addItem(i);
    }

    public Inventory give() {
        return inventory;
    }
}
