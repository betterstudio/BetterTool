package fr.better.tools.config;

import com.sun.org.apache.xerces.internal.xs.StringList;
import fr.better.tools.utils.ICreate;
import fr.better.tools.utils.Utility;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageManager {

    private final BetterConfig config;

    public MessageManager(BetterConfig config) {
        this.config = config;
    }

    /// SHOP CATEGORY

    public List<String> getLore(int buyPrice, int sellPrice, boolean canBuy, boolean canSell, String noValue){
        List<String> all = config.getStringList("Shop.items.lore");

        String buy, sell;

        if(canBuy){
            buy = buyPrice + "";
        }else{
            buy = noValue;
        }

        if(canSell){
            sell = sellPrice + "";
        }else{
            sell = noValue;
        }

        if(all == null){
            all = new ArrayList<>();

            all.add("§7Acheter : §6!buy!");
            all.add("§7Vendre : §6!sell!");
        }

        return Utility.replaceAll(config.getStringList("Shop.data.items.lore"),
                new Change("!buy!", buy), new Change("!sell!", sell));
    }

    public String getAllSell(){
        return config.getMessage("Shop.gui.allsell", true, "§8§o#tout-vendre");
    }

    public String getBuy(){
        return config.getMessage("Shop.gui.buy", true, "§8§o#acheter");
    }

    public String getSell(){
        return config.getMessage("Shop.gui.sell", true, "§8§o#vendre");
    }

    public String getValidatePrefix(){
        return config.getMessage("Shop.gui.validate.prefix", true, "§3Valider");
    }

    public String getDataPrefix(){
        return config.getMessage("Shop.gui.data.prefix", "§3Choisir");
    }

    public String getAccept(){
        return config.getMessage("Shop.gui.validate.accept", "§aACCEPTER");
    }

    public String getDeny(){
        return config.getMessage("Shop.gui.validate.deny", "§4REFUSER");
    }

    public ItemStack getValidItem(){
        return config.getItemStack("Shop.gui.data.items.validate", new ICreate(Material.SIGN).setName("§6Validez !").build());
    }

    public ItemStack getMoreItem(){
        return config.getItemStack("Shop.gui.data.items.moreItems", new ICreate(Material.APPLE).setHead("natatos").setName("§aAcheter +").build());
    }

    public String getMessageWhenSell(int size, String why, int money){
        return config.getMessage("Shop.message.sell", true,
                new Change("!size!", size+""),
                new Change("!gain!", money+""),
                new Change("!content!", why));
    }

    public String getMessageWhenSellAll(String why, int money){
        return config.getMessage("Shop.message.allsell", true, "§3Tu as gagné " + money + "€ en vendant toutes tes " +why+ "!",
                new Change("!content!", why),
                new Change("!gain!", money+""));
    }

    public String getMessageWhenBuy(int size, String why, int money){
        return config.getMessage("Shop.message.buy", true, "§3Tu as acheté " + size + " x " + why + " en payant : " + money + " € !",
                new Change("!content!", why),
                new Change("!price!", money+""),
                new Change("!size!", size+"")
        );
    }

    public String getErrorMessageHasntMoney(){
        return config.getMessage("Shop.message.error.noMoney", true, "§4Tu n'as pas assez d'argent pour faire cela !");
    }

    public String getErrorMessageHasntItem(){
        return config.getMessage("Shop.message.error.noItem", true, "§7Tu n'as pas assez d'item dans ton inventaire !");
    }

    public String getErrorMessageHasntAnyItem(){
        return config.getMessage("Shop.message.error.hasitem", true, "§7Tu n'as pas cet item dans ton inventaire !");
    }

    ///// COMMANDS CATEGORY

    public String getErrorMessageNoPermission(){
        try{
            return config.getMessage("Command.noPermission", true, "§7Tu n'as pas la permission d'executer cette commande !");
        }catch(Exception e){
            return "§7Tu n'as pas la permission d'executer cette commande";
        }
    }

    public String getErrorMessageNoArguments(){
        try{
            return config.getMessage("Command.noArgument", true, "§7Cette sous-commande n'existe pas !");
        }catch(Exception e){
            return "§7Cette sous-commande n'existe pas !";
        }
    }
}







