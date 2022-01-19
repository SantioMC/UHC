package me.santio.uhc.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStartEvent extends Event {
    
    private final HandlerList HANDLER_LIST = new HandlerList();
    
    public GameStartEvent() {}
    
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
