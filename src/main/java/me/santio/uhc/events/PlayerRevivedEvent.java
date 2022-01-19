package me.santio.uhc.events;

import lombok.Getter;
import lombok.Setter;
import me.santio.uhc.models.GamePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerRevivedEvent extends Event implements Cancellable {
    
    @Setter @Getter private boolean cancelled = false;
    @Getter private final GamePlayer player;
    @Getter private final GamePlayer admin;
    private final HandlerList HANDLER_LIST = new HandlerList();
    
    public PlayerRevivedEvent(GamePlayer player, GamePlayer admin) {
        this.player = player;
        this.admin = admin;
    }
    
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
