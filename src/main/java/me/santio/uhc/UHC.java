package me.santio.uhc;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class UHC extends JavaPlugin {
    
    @Getter private final ChatColor mainColor = ChatColor.of("#6666ff");
    
    @Override
    public void onEnable() {
    }
    
    @Override
    public void onDisable() {
    }
}
