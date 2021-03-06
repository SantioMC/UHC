package me.santio.uhc.listeners

import me.santio.uhc.Game
import me.santio.uhc.models.GamePlayer
import me.santio.uhc.sidebars.ScoreboardService
import me.santio.uhc.states.PlayerState
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object JoinListener : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val gamePlayer = GamePlayer(event.player.uniqueId)
        gamePlayer.playerState =
            if (Game.started) PlayerState.DEAD else PlayerState.LOBBY
        Game.players[event.player.uniqueId] = gamePlayer

        // Update Sidebar
        for (player in Game.players.values) ScoreboardService.join(player)
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        Game.players.remove(event.player.uniqueId)
    }
}