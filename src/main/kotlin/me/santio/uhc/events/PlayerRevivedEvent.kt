package me.santio.uhc.events

import lombok.Getter
import lombok.Setter
import me.santio.uhc.models.GamePlayer
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerRevivedEvent(@field:Getter private val player: GamePlayer, @field:Getter private val admin: GamePlayer) :
    Event(), Cancellable {
    @Setter
    @Getter
    private val cancelled = false
    private val HANDLER_LIST = HandlerList()
    override fun getHandlers(): HandlerList {
        return HANDLER_LIST
    }
}