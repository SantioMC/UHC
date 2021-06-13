package me.santio.uhc.models;

import lombok.Getter;
import lombok.Setter;
import me.santio.uhc.Game;
import me.santio.uhc.exceptions.DuplicateScenarioException;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import java.util.HashMap;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public abstract class Scenario implements Listener {

    @Setter @Getter private boolean active = false;
    @Setter @Getter private Material icon = Material.RED_DYE;
    @Getter private final HashMap<String, ScenarioData> data = new HashMap<>();
    @Getter private final String name;
    @Getter private final String id;
    
    public Scenario(String name) throws DuplicateScenarioException {
        this.name = name;
        this.id = this.name.replaceAll(" ", "_").toLowerCase();
        if (Game.getAllScenarios().containsKey(getId()))
            throw new DuplicateScenarioException("Scenario already exists with id "+getId()+" (Name: "+getName()+")");
        
        Game.getAllScenarios().put(getId(), this);
    }
    
    /**
     * Creates a data field for a scenario
     *
     * @param data The scenario data
     * @return The provided scenario data
     */
    public ScenarioData createData(ScenarioData data) {
        getData().put(data.getKey(), data);
        return data;
    }
    
    /**
     * Triggered when the game starts, this is right when
     * the countdown starts.
     */
    public void onGameStart() {}
    
    /**
     * Triggered when the game ends, this is right after the last player
     * kills the remaining player and the game is announced over
     * at the end of the game.
     */
    public void onGameEnd() {}
    
    /**
     * Triggered when a player is killed
     *
     * @param player The player's game profile
     */
    public void onPlayerDeath(GamePlayer player) {}
    
    /**
     * Triggered when a player has gotten a player kill.
     *
     * @param killer The killer's game profile
     * @param victim The victim's game profile
     */
    public void onPlayerKill(GamePlayer killer, GamePlayer victim) {}
    
    /**
     * Triggered when a player has gotten a entity kill.
     * (This includes player kills)
     *
     * @param player The player's game profile
     */
    public void onEntityKill(GamePlayer player) {}
    
    /**
     * Triggered when a player was revived by an admin.
     *
     * @param player The revived player's game profile
     */
    public void onPlayerRevived(GamePlayer player) {}
    
}
