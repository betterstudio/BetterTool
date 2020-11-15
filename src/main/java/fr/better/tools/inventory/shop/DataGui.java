package fr.better.tools.inventory.shop;

import fr.better.tools.inventory.GuiCreator;
import fr.better.tools.inventory.action.ClickAction;
import fr.better.tools.utils.ICreate;
import fr.better.tools.utils.SizeValue;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class DataGui extends GuiCreator {

    private final ItemShop shop;
    private int size;
    private boolean isMore;
    private final DAction action;

    private static ItemStack plus1, plus10, set0, minus1, minus10, set64, wantmore, valid, stack1, stack2, stack3, stack5, stack7, stack9, stack12;

    public static void setup(){
        plus1 = new ICreate(Material.STAINED_GLASS_PANE, 1, (short) 5).setName("§a+1").build();
        plus10 = new ICreate(Material.STAINED_GLASS_PANE, 1, (short) 5).setName("§a+10").build();
        set64 = new ICreate(Material.STAINED_GLASS_PANE, 1, (short) 5).setName("§a+64").build();
        minus1 = new ICreate(Material.STAINED_GLASS_PANE, 1, (short) 14).setName("§a-1").build();
        minus10 = new ICreate(Material.STAINED_GLASS_PANE, 1, (short) 14).setName("§a-10").build();
        set0 = new ICreate(Material.STAINED_GLASS_PANE, 1, (short) 14).setName("§a-64").build();
        wantmore = new ICreate(Material.APPLE).setHead("natatos").setName("§aAcheter +").build();
        stack1 = new ICreate(Material.STAINED_GLASS,1, (short) 5).setName("§a1 stack").build();
        stack2 = new ICreate(Material.STAINED_GLASS, 2, (short) 5).setName("§a2 stack").build();
        stack3 = new ICreate(Material.STAINED_GLASS, 3, (short) 5).setName("§a3 stack").build();
        stack5 = new ICreate(Material.STAINED_GLASS, 5, (short) 5).setName("§a5 stack").build();
        stack7 = new ICreate(Material.STAINED_GLASS, 7, (short) 5).setName("§a7 stack").build();
        stack9 = new ICreate(Material.STAINED_GLASS, 9, (short) 5).setName("§a9 stack").build();
        stack12 = new ICreate(Material.STAINED_GLASS, 12, (short) 5).setName("§a12 stack").build();
        valid = new ICreate(Material.SIGN).setName("§6Validez !").build();
    }

    public DataGui(String name, ItemShop item, Player p, DAction action) {

        super(name, 3);

        this.shop = item;
        this.action = action;
        this.size = 1;
        this.isMore = false;

        item.getView().setAmount(1);

        setTopLine(7);
        setBackLine(7);
        setSideWays(7);

        set(plus1, 14);
        set(plus10, 15);
        set(set64, 16);

        set(minus1, 12);
        set(minus10, 11);
        set(set0, 10);

        set(wantmore, 22);
        set(valid, 26);

        set(item.getView(), 13);

        setAction(new ClickAction() {
            @Override
            public void action(InventoryClickEvent event) {
                event.setCancelled(true);
                ItemStack current = event.getCurrentItem();
                Player p = (Player) event.getWhoClicked();

                if(current == null)return;
                if(!isMore){
                    a1(event.getSlot());
                    if(a2(event.getSlot(), p)){
                        item.getView().setAmount(size);
                        set(item.getView(), 13);
                        p.updateInventory();
                    }
                }else{
                    a5(event.getSlot(), p);
                }
            }
        });
        p.openInventory(inventory);
    }

    private void a5(int slot, Player p) {
        switch(slot){
            case 16:
                size=12*64;
                break;
            case 15:
                size=9*64;
                break;
            case 14:
                size=7*64;
                break;
            case 13:
                size=5*64;
                break;
            case 12:
                size=3*64;
                break;
            case 11:
                size=2*64;
                break;
            case 10:
                size=1*64;
                break;
            default:return;
        }
        a4(p);
    }

    private boolean a2(int slot, Player player) {
        switch(slot){
            case 22:
                a3();
                return false;
            case 26:
                a4(player);
                return false;
        }
        return true;
    }

    private void a3() {
        inventory.clear();
        setSideWays(9);
        setTopLine(9);
        setBackLine(9);
        add(stack1, stack2, stack3, stack5, stack7, stack9, stack12);
        isMore = true;
    }

    private void a4(Player player) {
        player.closeInventory();
        action.execute(new SizeValue(size));
    }


    private void a1(int slot){
        switch(slot){
            case 14:
                size=size+1;
                break;
            case 15:
                size=size+10;
                break;
            case 16:
                size=64;
                break;
            case 12:
                size=size-1;
                break;
            case 11:
                size=size-10;
                break;
            case 10:
                size=1;
                break;
            default:
                return;
        }
        if(size < 1)size = 1;
        if(size > 64)size = 64;
    }
}
