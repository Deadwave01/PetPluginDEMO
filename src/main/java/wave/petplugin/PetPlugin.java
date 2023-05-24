package wave.petplugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import wave.petplugin.commands.PetCommands;
import wave.petplugin.pet.LoadLists;
import wave.petplugin.pet.Pet;
import wave.petplugin.petevent.PetEvent;

public final class PetPlugin extends JavaPlugin {

    private static Plugin Instance;


    @Override
    public void onEnable() {
        Instance = this;
        saveDefaultConfig();
        LoadLists.loadPetList();
        LoadLists.loadToolsList();
        getCommand("pet").setExecutor(new PetCommands());
        getServer().getPluginManager().registerEvents(new PetEvent(),getInstance());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance() {
        return Instance;
    }
}
