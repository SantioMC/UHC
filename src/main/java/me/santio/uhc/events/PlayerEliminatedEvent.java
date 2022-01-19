package me.santio.uhc.events;

import lombok.Getter;
import lombok.Setter;
import me.santio.uhc.models.GamePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerEliminatedEvent extends Event {
    
    @Getter private final GamePlayer player;
    private final HandlerList HANDLER_LIST = new HandlerList();
    
    public PlayerEliminatedEvent(GamePlayer player) {
        this.player = player;
    }
    
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
