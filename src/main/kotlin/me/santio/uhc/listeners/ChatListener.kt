package me.santio.uhc.listeners

import org.bukkit.event.EventPriority
import org.bukkit.event.player.AsyncPlayerChatEvent
import me.santio.uhc.models.GamePlayer
import me.santio.uhc.Game
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class ChatListener : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onChat(event: AsyncPlayerChatEvent) {
        if (!event.isCancelled) {
            val gamePlayer = Game.getPlayers()[event.player.uniqueId] ?: return
            val hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, gamePlayer.chatHover)
            val component = TextComponent.fromLegacyText(event.format)
            component[0].hoverEvent = hoverEvent
            event.isCancelled = true
            Bukkit.getOnlinePlayers().forEach { player: Player? -> player!!.spigot().sendMessage(*component) }
        }
    }
}