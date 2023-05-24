package wave.petplugin.pet;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import wave.petplugin.PetPlugin;
import wave.petplugin.api.NearbyEntities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Pet {

    private String name;
    private Material material;
    private ChatColor color;
    private static NamespacedKey key = NamespacedKey.fromString("pet");

    public Pet(String name, Material material, ChatColor color) {
        this.name = name;
        this.material = material;
        this.color = color;
    }

    public void summonPet(Player player){
        Location playerLocation = player.getLocation();
        double distanceOffset = -1.5;
        Vector direction = player.getLocation().getDirection().normalize();
        double dx = direction.getX()*distanceOffset;
        double dy = direction.getY()*distanceOffset+0.5;
        double dz = direction.getZ()*distanceOffset;
        Location petLocation = playerLocation.clone().add(dx,dy,dz);
        ArmorStand armorstand = (ArmorStand) player.getWorld().spawnEntity(petLocation, EntityType.ARMOR_STAND);
        armorstand.getPersistentDataContainer().set(key, PersistentDataType.STRING, player.getUniqueId()+ getName());
        armorstand.setGravity(false);
        armorstand.setVisible(false);
        armorstand.setInvulnerable(true);
        armorstand.setArms(true);
        armorstand.setCustomNameVisible(true);
        ItemStack itemStack = new ItemStack(getMaterial());
        if(LoadLists.getToolsList().contains(getMaterial())){
            armorstand.setSmall(false);
            armorstand.setRightArmPose(new EulerAngle(90F,0,0));
            armorstand.setItemInHand(itemStack);
        } else {
            armorstand.setSmall(true);
            armorstand.setHelmet(itemStack);
        }
        armorstand.setCustomName(getColor()  + getName() + " Владелец: " + player.getName());
    }

    public static Entity getPetPlayer(Player player){
        Location location = player.getLocation();
        int radius = 6;
        List<Entity> entities = new ArrayList<>(Arrays.asList(NearbyEntities.getNearbyEntities(location,radius)));
        for(Entity e : entities){
            for(String keys : PetPlugin.getInstance().getConfig().getKeys(false)) {
                String strcheck = player.getUniqueId() + PetPlugin.getInstance().getConfig().getString(keys + ".name");
                if (e.getType() == EntityType.ARMOR_STAND && e.getPersistentDataContainer().get(key, PersistentDataType.STRING).equalsIgnoreCase(strcheck)) {
                    return e;
                }
            }
        }
        return null;
    }

    public void removePet(Player player){
        if(getPetPlayer(player) != null){
            String strcheck = player.getUniqueId() + getName();
            if(getPetPlayer(player).getPersistentDataContainer().get(key,PersistentDataType.STRING).equalsIgnoreCase(strcheck)) {
                getPetPlayer(player).remove();
                player.sendMessage(ChatColor.GREEN + "Ваш питомец был успешно удален!");
            }
        }
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public ChatColor getColor() {
        return color;
    }
}
