package wave.petplugin.petevent;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import wave.petplugin.pet.Pet;

public class PetEvent implements Listener {

    @EventHandler
    public void petMovetoPlayer(PlayerMoveEvent e){
        Player player = e.getPlayer();
        Location playerLocation = player.getLocation();
        double distanceOffset = -1.5;
        if(Pet.getPetPlayer(player) != null){
            ArmorStand armorStand = (ArmorStand) Pet.getPetPlayer(player);;
            Vector direction = player.getLocation().getDirection().normalize();
            double dx = direction.getX()*distanceOffset+1;
            double dy = direction.getY()*distanceOffset+1;
            double dz = direction.getZ()*distanceOffset;
            Location petLocation = playerLocation.clone().add(dx,dy,dz);
            armorStand.teleport(petLocation);
        }
    }
}
