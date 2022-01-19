package me.santio.uhc;

import lombok.Getter;
import me.santio.uhc.exceptions.DuplicateScenarioException;
import me.santio.uhc.listeners.ChatListener;
import me.santio.uhc.listeners.JoinListener;
import me.santio.uhc.scenarios.DoubleDropsScenario;
import me.santio.uhc.utils.FreezeUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public final class UHC extends JavaPlugin {
    
    @Getter private static final ChatColor mainColor = ChatColor.of("#6666f9");
    @Getter private static final Location lobby = new Location(Bukkit.getWorlds().get(0), 0, 1, 0);
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new FreezeUtils(), this);
    
        FreezeUtils.reset();
        
        try {
            loadScenarios();
        } catch (DuplicateScenarioException e) {
            e.printStackTrace();
            this.setEnabled(false);
        } if (!this.isEnabled()) return;
    }
    
    @Override
    public void onDisable() {
        FreezeUtils.reset();
    }
    
    public void loadScenarios() throws DuplicateScenarioException {
        getServer().getPluginManager().registerEvents(new DoubleDropsScenario(), this);
    }
    
    public static UHC getInstance() {
        return getPlugin(UHC.class);
    }
}
