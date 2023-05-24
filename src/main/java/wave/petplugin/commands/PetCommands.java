package wave.petplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wave.petplugin.pet.LoadLists;
import wave.petplugin.pet.Pet;

public class PetCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Pet pet = getPet(args[1]);
            if(args[0].equalsIgnoreCase("summon")){
                if(pet != null){
                    pet.summonPet(p);
                    return true;
                } else {
                    p.sendMessage(ChatColor.RED + "Питомец не найден!");
                    return true;
                }
            } else if (args[0].equalsIgnoreCase("remove")){
                if(pet != null){
                    pet.removePet(p);
                    return true;
                } else {
                    p.sendMessage(ChatColor.RED + "Питомец не найден!");
                    return true;
                }
            } else {
                p.sendMessage(ChatColor.RED + "Комманда не найдена!");
                return true;
            }
        }
        return false;
    }

    private Pet getPet(String str){
        for(Pet p : LoadLists.getPetList()){
            if(p.getName().equalsIgnoreCase(str)){
                return p;
            }
        }
        return null;
    }
}
