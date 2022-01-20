package me.santio.uhc.events

import me.santio.uhc.models.GamePlayer
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerEliminatedEvent(private val player: GamePlayer) : Event() {
    private val HANDLER_LIST = HandlerList()
    override fun getHandlers(): HandlerList = HANDLER_LIST
}