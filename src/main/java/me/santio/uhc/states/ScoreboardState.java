package me.santio.uhc.states;

import lombok.Getter;
import me.santio.uhc.sidebars.LobbySidebar;
import me.santio.uhc.sidebars.Sidebar;

public enum ScoreboardState {
    LOBBY(new LobbySidebar()),
    IN_GAME(null),
    ADMIN(null);
    
    @Getter private final Sidebar sidebar;
    ScoreboardState(Sidebar sidebar) {
        this.sidebar = sidebar;
    }
}
