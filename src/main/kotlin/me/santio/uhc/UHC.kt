package me.santio.uhc

import lombok.Getter
import me.santio.uhc.utils.FreezeUtils.Companion.freeze
import me.santio.uhc.utils.ChatUtils.tacc
import me.santio.uhc.utils.FreezeUtils.Companion.reset
import java.util.HashMap
import java.util.UUID
import me.santio.uhc.models.GamePlayer
import me.santio.uhc.models.Scenario
import org.bukkit.scheduler.BukkitTask
import me.santio.uhc.events.GameStartEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import me.santio.uhc.Game
import me.santio.uhc.utils.FreezeUtils
import org.bukkit.scheduler.BukkitRunnable
import me.santio.uhc.UHC
import me.santio.uhc.utils.ChatUtils
import org.bukkit.plugin.java.JavaPlugin
import me.santio.uhc.listeners.JoinListener
import me.santio.uhc.listeners.ChatListener
import me.santio.uhc.exceptions.DuplicateScenarioException
import kotlin.Throws
import me.santio.uhc.scenarios.DoubleDropsScenario
import net.md_5.bungee.api.ChatColor
import org.bukkit.Location

class UHC : JavaPlugin() {
    override fun onEnable() {
        server.pluginManager.registerEvents(JoinListener(), this)
        server.pluginManager.registerEvents(ChatListener(), this)
        server.pluginManager.registerEvents(FreezeUtils(), this)
        reset()
        try {
            loadScenarios()
        } catch (e: DuplicateScenarioException) {
            e.printStackTrace()
            isEnabled = false
        }
        if (!isEnabled) return
    }

    override fun onDisable() {
        reset()
    }

    @Throws(DuplicateScenarioException::class)
    fun loadScenarios() {
        server.pluginManager.registerEvents(DoubleDropsScenario(), this)
    }

    companion object {
        @Getter
        private val mainColor = ChatColor.of("#6666f9")

        @Getter
        private val lobby = Location(Bukkit.getWorlds()[0], 0, 1, 0)
        val instance: UHC
            get() = getPlugin(UHC::class.java)
    }
}