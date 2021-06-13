package me.santio.uhc.listeners;

import me.santio.uhc.Game;
import me.santio.uhc.states.PlayerState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Game.getPlayerState().put(event.getPlayer().getUniqueId(), Game.isRunning() ? PlayerState.DEAD : PlayerState.LOBBY);
        
    }
    
}
