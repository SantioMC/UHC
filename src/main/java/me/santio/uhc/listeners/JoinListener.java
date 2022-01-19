package me.santio.uhc.listeners;

import me.santio.uhc.Game;
import me.santio.uhc.models.GamePlayer;
import me.santio.uhc.sidebars.LobbySidebar;
import me.santio.uhc.states.PlayerState;
import me.santio.uhc.utils.FreezeUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        GamePlayer gamePlayer = new GamePlayer(event.getPlayer().getUniqueId());
        gamePlayer.setPlayerState(Game.isStarted() ? PlayerState.DEAD : PlayerState.LOBBY);
        gamePlayer.setScoreboardState(gamePlayer.getPlayerState().getScoreboard());
        gamePlayer.getScoreboardState().getSidebar().apply(event.getPlayer());
        Game.getPlayers().put(event.getPlayer().getUniqueId(), gamePlayer);
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Game.getPlayers().remove(event.getPlayer().getUniqueId());
    }
    
}
