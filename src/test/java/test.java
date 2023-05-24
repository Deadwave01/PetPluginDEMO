import org.bukkit.ChatColor;
import org.junit.Test;
import wave.petplugin.pet.LoadLists;

import java.util.HashMap;

public class test {

    private static HashMap<String, ChatColor> ChatColorValues = new HashMap<>();

    @Test
    public void test(){
        LoadLists.loadChatcolorValues();
        System.out.println(LoadLists.getChatColorValues().get("RED").asBungee());
    }

    public static void loadChatcolorValues(){
        ChatColor[] chatColors = ChatColor.values();
        for(ChatColor color : chatColors){
            ChatColorValues.put(color.name(),color);
        }
    }
}
