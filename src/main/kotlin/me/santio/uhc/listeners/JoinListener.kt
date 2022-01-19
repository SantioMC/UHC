package me.santio.uhc.listeners

import org.bukkit.event.EventPriority
import org.bukkit.event.player.AsyncPlayerChatEvent
import me.santio.uhc.models.GamePlayer
import me.santio.uhc.Game
import me.santio.uhc.states.PlayerState
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class JoinListener : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val gamePlayer = GamePlayer(event.player.uniqueId)
        gamePlayer.playerState =
            if (Game.isStarted()) PlayerState.DEAD else PlayerState.LOBBY
        gamePlayer.scoreboardState = gamePlayer.playerState.scoreboard
        gamePlayer.scoreboardState.sidebar.apply(event.player)
        Game.getPlayers()[event.player.uniqueId] = gamePlayer
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        Game.getPlayers().remove(event.player.uniqueId)
    }
}