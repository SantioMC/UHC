package me.santio.uhc.models

import me.santio.uhc.Game
import me.santio.uhc.UHC
import me.santio.uhc.exceptions.DuplicateScenarioException
import org.bukkit.Material
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import java.util.*

abstract class Scenario(val name: String) : Listener {
    private val active = false
   var icon = Material.RED_DYE
    private val data = HashMap<String, ScenarioData>()
    private val id: String = name.replace(" ".toRegex(), "_").lowercase(Locale.getDefault())
    private var disabled = false

    init {
        if (Game.allScenarios.containsKey(id)) throw DuplicateScenarioException("Scenario already exists with id $id (Name: $name)")
        Game.allScenarios[id] = this
    }

//    /**
//     * Creates a data field for a scenario
//     *
//     * @param data The scenario data
//     * @return The provided scenario data
//     */
//    fun createData(data: ScenarioData): ScenarioData {
//        return data
//    }

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
        UHC.get().server.pluginManager.registerEvents(this, UHC.get())
    }
}