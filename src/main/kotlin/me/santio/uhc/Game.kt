package me.santio.uhc

import me.santio.uhc.events.GameStartEvent
import me.santio.uhc.models.GamePlayer
import me.santio.uhc.models.Scenario
import me.santio.uhc.utils.ChatUtils
import me.santio.uhc.utils.ChatUtils.tacc
import me.santio.uhc.utils.FreezeUtils
import me.santio.uhc.utils.FreezeUtils.Companion.freeze
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import java.util.*
import java.util.function.Consumer

object Game {
    var starting = false
    var started = false
    var countdown = 0
    var players = HashMap<UUID, GamePlayer>()
    var allScenarios = HashMap<String, Scenario>()
    var activeScenarios = ArrayList<Scenario>()
    var alive = ArrayList<UUID>()
    var countdowner: BukkitTask? = null

    /**
     * Attempts to start the game
     */
    fun attemptStart() {
        if (starting || started) return
        countdown = 60
        val startEvent = GameStartEvent()
        Bukkit.getPluginManager().callEvent(startEvent)
        Bukkit.getOnlinePlayers().forEach(Consumer { p: Player ->
            alive.add(p.uniqueId)
            freeze(p)
        })
        countdowner = object : BukkitRunnable() {
            override fun run() {
                if (!starting) {
                    reset()
                } else if (countdown <= 0) {
                    initiateStart()
                } else {
                    countdown--
                    if (countdown % 5 == 0 || countdown < 5) {
                        Bukkit.broadcastMessage(tacc("${UHC.mainColor}UHC &8| &7The game will begin in ${UHC.mainColor}${countdown} seconds&7!"))
                    }
                }
            }
        }.runTaskTimer(UHC.get(), 20, 20)
    }

    private fun initiateStart() {
        starting = false
        started = true
        FreezeUtils.reset()
    }

    fun reset() {
        started = false
        starting = false
        countdown = 0
        countdowner!!.cancel()
        countdowner = null
        FreezeUtils.reset()
        Bukkit.getOnlinePlayers().forEach(Consumer { player: Player -> player.teleport(UHC.lobby) })
    }
}
