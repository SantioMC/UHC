package me.santio.uhc.listeners

import me.santio.uhc.Game
import me.santio.uhc.UHC
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.chat.hover.content.Text
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.util.*

object ChatListener : Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onChat(event: AsyncPlayerChatEvent) {
        if (!event.isCancelled) {
            val gamePlayer = Game.players[event.player.uniqueId] ?: return

            val original: String = try { String.format(event.format, event.player.name, event.message) }
            catch(e: MissingFormatArgumentException) { event.format }

            val component = TextComponent.fromLegacyText(original)
            component[0].hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(gamePlayer.chatHover))

            event.isCancelled = true
            Bukkit.getOnlinePlayers().forEach { player: Player -> player.spigot().sendMessage(*component) }
        }
    }
}