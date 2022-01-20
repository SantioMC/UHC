package me.santio.uhc.events

import me.santio.uhc.models.GamePlayer
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerRevivedEvent(private val player: GamePlayer, private val admin: GamePlayer) : Event(), Cancellable {
    private var cancelled = false
    private val HANDLER_LIST = HandlerList()

    override fun getHandlers(): HandlerList = HANDLER_LIST
    override fun isCancelled(): Boolean = cancelled

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }
}