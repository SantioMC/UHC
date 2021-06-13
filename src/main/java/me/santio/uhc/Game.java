package me.santio.uhc;

import lombok.Getter;
import me.santio.uhc.models.GamePlayer;
import me.santio.uhc.models.Scenario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Game {
    
    @Getter private static final HashMap<UUID, GamePlayer> players = new HashMap<>();
    @Getter private static boolean running = false;
    @Getter private static final HashMap<String, Scenario> allScenarios = new HashMap<>();
    @Getter private static final ArrayList<Scenario> activeScenarios = new ArrayList<>();
    
}
