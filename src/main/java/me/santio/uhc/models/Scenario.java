package me.santio.uhc.models;

import lombok.Getter;
import lombok.Setter;
import me.santio.uhc.Game;
import me.santio.uhc.UHC;
import me.santio.uhc.exceptions.DuplicateScenarioException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.HashMap;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public abstract class Scenario implements Listener {

    @Setter @Getter private boolean active = false;
    @Setter @Getter private Material icon = Material.RED_DYE;
    @Getter private final HashMap<String, ScenarioData> data = new HashMap<>();
    @Getter private final String name;
    @Getter private final String id;
    @Getter private boolean disabled;
    
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
     * Stops running the scenario events and flags it as disabled
     */
    public void disable() {
        disabled = true;
        HandlerList.unregisterAll(this);
    }

    /**
     * Registers the scenario and its EventHandlers and marks it as enabled
     */
    public void enable() {
        disabled = false;
        UHC.getInstance().getServer().getPluginManager().registerEvents(this, UHC.getInstance());
    }
}
