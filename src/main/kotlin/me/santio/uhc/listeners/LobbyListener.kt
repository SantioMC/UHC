package me.santio.uhc.listeners

import org.bukkit.event.EventPriority
import org.bukkit.event.player.AsyncPlayerChatEvent
import me.santio.uhc.models.GamePlayer
import me.santio.uhc.Game
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class LobbyListener : Listener