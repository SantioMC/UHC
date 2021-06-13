package me.santio.uhc;

import lombok.Getter;
import me.santio.uhc.states.PlayerState;

import java.util.HashMap;
import java.util.UUID;

public class Game {
    
    @Getter private static final HashMap<UUID, PlayerState> playerState = new HashMap<>();
    @Getter private static boolean running = false;
    
}
