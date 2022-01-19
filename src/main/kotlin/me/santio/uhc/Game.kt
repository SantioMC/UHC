package me.santio.uhc

import lombok.Getter
import lombok.Setter
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
import java.util.ArrayList
import java.util.function.Consumer

object Game {
    @Setter
    @Getter
    private val starting = false

    @Setter
    @Getter
    private val started = false

    @Setter
    @Getter
    private val countdown = 0

    @Getter
    private val players = HashMap<UUID, GamePlayer>()

    @Getter
    private val allScenarios = HashMap<String, Scenario>()

    @Getter
    private val activeScenarios = ArrayList<Scenario>()

    @Getter
    private val alive = ArrayList<UUID>()
    private var countdowner: BukkitTask? = null

    /**
     * Attempts to start the game
     */
    fun attemptStart() {
        if (Game.isStarting() || Game.isStarted()) return
        Game.setCountdown(60)
        val startEvent = GameStartEvent()
        Bukkit.getPluginManager().callEvent(startEvent)
        Bukkit.getOnlinePlayers().forEach(Consumer { p: Player ->
            alive.add(p.uniqueId)
            freeze(p)
        })
        countdowner = object : BukkitRunnable() {
            override fun run() {
                if (!Game.isStarting()) {
                    reset()
                } else if (Game.getCountdown() <= 0) {
                    initiateStart()
                } else {
                    Game.setCountdown(Game.getCountdown() - 1)
                    if (Game.getCountdown() % 5 == 0 || Game.getCountdown() < 5) {
                        Bukkit.broadcastMessage(
                            UHC.getMainColor()
                                .toString() + "UHC" + tacc(" &8| &7The game will begin in ") + UHC.getMainColor() + Game.getCountdown() + " seconds" + tacc(
                                "&7!"
                            )
                        )
                    }
                }
            }
        }.runTaskTimer(UHC.Companion.getInstance(), 20, 20)
    }

    private fun initiateStart() {
        Game.setStarting(false)
        Game.setStarted(true)
        FreezeUtils.reset()
    }

    fun reset() {
        Game.setStarted(false)
        Game.setStarting(false)
        Game.setCountdown(0)
        countdowner!!.cancel()
        countdowner = null
        FreezeUtils.reset()
        Bukkit.getOnlinePlayers().forEach(Consumer { player: Player -> player.teleport(UHC.getLobby()) })
    }
}