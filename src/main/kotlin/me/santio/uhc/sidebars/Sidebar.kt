package me.santio.uhc.sidebars

import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Team

abstract class Sidebar(val id: String, val title: String) {
    @Transient
    private val manager = Bukkit.getScoreboardManager()!!

    @Transient
    private val scoreboard: Scoreboard = manager.newScoreboard

    @Transient
    private val objective: Objective = scoreboard.registerNewObjective(
        "uhc_$id", "dummy",
        ChatColor.translateAlternateColorCodes('&', title)
    )

    init {
        objective.displaySlot = DisplaySlot.SIDEBAR
        for (i in 0..14) {
            val team: Team = scoreboard.registerNewTeam("uhc_" + id + "_" + i)
            team.addEntry(ChatColor.values()[i].toString() + "§r")
            lines[i] = team
        }
    }

    fun apply(player: Player) {
        player.scoreboard = scoreboard
    }

    fun remove(player: Player) {
        player.scoreboard = manager.mainScoreboard
    }

    fun update() {
        for (i in 0..14) {
            objective.getScore(ChatColor.values()[i].toString() + "§r").score = i
        }
    }

    fun delete() {
        for (team in lines) {
            team!!.unregister()
        }
        objective.unregister()
    }

    fun setLine(line: Int, text: String) {
        val colored = ChatColor.translateAlternateColorCodes('&', text)
        val team = lines[15 - line]
        if (text.length <= 16) team!!.prefix = colored else {
            team!!.prefix = colored.substring(0, 16)
            team.suffix = colored.substring(16)
        }
        update()
    }

    companion object {
        val lines = arrayOfNulls<Team>(15)
    }
}