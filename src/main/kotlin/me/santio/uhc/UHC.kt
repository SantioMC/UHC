package me.santio.uhc

import me.santio.uhc.exceptions.DuplicateScenarioException
import me.santio.uhc.listeners.ChatListener
import me.santio.uhc.listeners.JoinListener
import me.santio.uhc.utils.FreezeUtils
import me.santio.uhc.utils.FreezeUtils.Companion.reset
import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class UHC : JavaPlugin() {
    override fun onEnable() {

        server.pluginManager.registerEvents(FreezeUtils(), this)
        server.pluginManager.registerEvents(JoinListener, this)
        server.pluginManager.registerEvents(ChatListener, this)
        reset()

        // Support Reloads
        for (player in Bukkit.getOnlinePlayers()) JoinListener.onJoin(PlayerJoinEvent(player, "null"))

        // Scenarios
        try { loadScenarios() }
        catch (e: DuplicateScenarioException) {
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
//        server.pluginManager.registerEvents(DoubleDropsScenario(), this)
    }

    companion object {
        val mainColor = ChatColor.of("#66b4f9")
        var lobby = Bukkit.getWorlds()[0].spawnLocation.add(0.5, 0.0, 0.5)

        fun get(): UHC {
            return getPlugin(UHC::class.java)
        }
    }
}