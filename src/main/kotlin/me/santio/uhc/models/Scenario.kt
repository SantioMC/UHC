package me.santio.uhc.models

import lombok.Getter
import lombok.Setter
import java.util.UUID
import me.santio.uhc.states.ScoreboardState
import org.bukkit.entity.Player
import org.bukkit.Bukkit
import net.md_5.bungee.api.chat.BaseComponent
import me.santio.uhc.UHC
import java.util.HashMap
import me.santio.uhc.models.ScenarioData
import java.util.Locale
import me.santio.uhc.Game
import me.santio.uhc.exceptions.DuplicateScenarioException
import org.bukkit.event.HandlerList
import org.apache.commons.lang.WordUtils
import org.bukkit.Material
import org.bukkit.event.Listener

abstract class Scenario(@field:Getter private val name: String) : Listener {
    @Setter
    @Getter
    private val active = false

    @Setter
    @Getter
    private val icon = Material.RED_DYE

    @Getter
    private val data = HashMap<String, ScenarioData>()

    @Getter
    private val id: String

    @Getter
    private var disabled = false

    init {
        id = name.replace(" ".toRegex(), "_").lowercase(Locale.getDefault())
        if (Game.getAllScenarios()
                .containsKey(getId())
        ) throw DuplicateScenarioException("Scenario already exists with id " + getId() + " (Name: " + getName() + ")")
        Game.getAllScenarios()[getId()] = this
    }

    /**
     * Creates a data field for a scenario
     *
     * @param data The scenario data
     * @return The provided scenario data
     */
    fun createData(data: ScenarioData): ScenarioData {
        getData().put(data.key, data)
        return data
    }

    /**
     * Stops running the scenario events and flags it as disabled
     */
    fun disable() {
        disabled = true
        HandlerList.unregisterAll(this)
    }

    /**
     * Registers the scenario and its EventHandlers and marks it as enabled
     */
    fun enable() {
        disabled = false
        UHC.instance.server.pluginManager.registerEvents(this, UHC.instance)
    }
}