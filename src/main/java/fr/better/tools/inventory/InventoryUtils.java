package fr.better.tools.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryUtils {

    protected final Inventory inventory;

    public InventoryUtils(Inventory inventory){
        this.inventory = inventory;
    }

    public List<ItemStack> getItemWhenIsSimilar(ItemStack item){
        List<ItemStack> result = new ArrayList<>();
        for(ItemStack similar : inventory.getContents()){
            if(item.isSimilar(similar))result.add(similar);
        }
        return result;
    }

    public boolean isFull(){
        return inventory.firstEmpty() == -1;
    }

    public void removeByAnSizeOf(int size, ItemStack similar){

        int i = -1;

        for(ItemStack item : inventory.getContents()){

            i++;

            if(item == null)continue;

            if(item.isSimilar(similar)){

                int amount = item.getAmount();

                if(size > amount){

                    size = size - amount;
                    inventory.remove(item);

                } else {

                    ItemStack it = item.clone();
                    it.setAmount(amount-size);
                    inventory.setItem(i, it);
                }
            }
        }
    }
}
