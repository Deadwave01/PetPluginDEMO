package wave.petplugin.pet;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import wave.petplugin.PetPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LoadLists {
    private static HashMap<String, ChatColor> ChatColorValues = new HashMap<>();
    private static List<Pet> petList = new ArrayList<>();
    private static List<Material> toolsList = new ArrayList<>();

    public static void loadChatcolorValues(){
        ChatColor[] chatColors = ChatColor.values();
        for(ChatColor color : chatColors){
            ChatColorValues.put(color.name(),color);
        }
    }



    public static void loadToolsList(){
        toolsList.add(Material.DIAMOND_AXE);
        toolsList.add(Material.DIAMOND_SWORD);
        toolsList.add(Material.DIAMOND_SHOVEL);
        toolsList.add(Material.DIAMOND_HOE);
        toolsList.add(Material.DIAMOND_PICKAXE);
        toolsList.add(Material.NETHERITE_AXE);
        toolsList.add(Material.NETHERITE_SWORD);
        toolsList.add(Material.NETHERITE_SHOVEL);
        toolsList.add(Material.NETHERITE_HOE);
        toolsList.add(Material.NETHERITE_PICKAXE);
        toolsList.add(Material.IRON_AXE);
        toolsList.add(Material.IRON_SWORD);
        toolsList.add(Material.IRON_SHOVEL);
        toolsList.add(Material.IRON_HOE);
        toolsList.add(Material.IRON_PICKAXE);
        toolsList.add(Material.STONE_AXE);
        toolsList.add(Material.STONE_SWORD);
        toolsList.add(Material.STONE_SHOVEL);
        toolsList.add(Material.STONE_HOE);
        toolsList.add(Material.STONE_PICKAXE);
        toolsList.add(Material.WOODEN_AXE);
        toolsList.add(Material.WOODEN_SWORD);
        toolsList.add(Material.WOODEN_SHOVEL);
        toolsList.add(Material.WOODEN_HOE);
        toolsList.add(Material.WOODEN_PICKAXE);
        toolsList.add(Material.BOW);
    }

    public static void loadPetList(){
        for(String keys : PetPlugin.getInstance().getConfig().getKeys(false)){
            String petName = PetPlugin.getInstance().getConfig().getString(keys + ".name");
            String stringMaterial = PetPlugin.getInstance().getConfig().getString(keys + ".material");
            loadChatcolorValues();
            ChatColor color = getChatColorValues().get(PetPlugin.getInstance().getConfig().getString(keys + ".color"));
            Material material = Material.matchMaterial(stringMaterial);
            if(color != null){
                petList.add(new Pet(petName,material,color));
            } else {
                color = ChatColor.WHITE;
                petList.add(new Pet(petName,material,color));
            }
        }
    }

    public static HashMap<String, ChatColor> getChatColorValues() {
        return ChatColorValues;
    }

    public static List<Pet> getPetList() {
        return petList;
    }

    public static List<Material> getToolsList() {
        return toolsList;
    }
}
