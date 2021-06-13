package me.santio.uhc.models;

import lombok.Getter;
import lombok.Setter;
import me.santio.uhc.states.PlayerState;
import me.santio.uhc.states.ScoreboardState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GamePlayer {
    
    @Setter @Getter private PlayerState playerState;
    @Setter @Getter private ScoreboardState scoreboardState;
    @Getter private final UUID player;
    
    public GamePlayer(UUID uniqueId) {
        this.player = uniqueId;
    }
    
    public Player fetchPlayer() {
        return Bukkit.getPlayer(getPlayer());
    }
}
