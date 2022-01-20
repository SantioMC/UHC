package me.santio.uhc.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class GameStartEvent : Event() {
    private val HANDLER_LIST = HandlerList()
    override fun getHandlers(): HandlerList = HANDLER_LIST
}