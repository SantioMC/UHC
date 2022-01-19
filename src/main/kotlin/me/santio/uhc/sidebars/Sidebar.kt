package me.santio.uhc.sidebars

import lombok.Getter
import me.santio.uhc.sidebars.Sidebar
import me.santio.uhc.UHC
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scoreboard.*

abstract class Sidebar(@field:Getter private val id: String, title: String?) {
    @Getter
    @Transient
    private val manager = Bukkit.getScoreboardManager()

    @Getter
    @Transient
    private val scoreboard: Scoreboard

    @Transient
    private val objective: Objective

    init {
        if (getManager() == null) return
        scoreboard = getManager().getNewScoreboard()
        objective = scoreboard.registerNewObjective(
            "uhc_$id", "dummy",
            ChatColor.translateAlternateColorCodes('&', title!!)
        )
        objective.displaySlot = DisplaySlot.SIDEBAR
        for (i in 0..14) {
            val team: Team = getScoreboard().registerNewTeam("uhc_" + id + "_" + i)
            team.addEntry(ChatColor.values()[i].toString() + "§r")
            lines[i] = team
        }
    }

    fun apply(player: Player) {
        player.scoreboard = getScoreboard()
    }

    fun remove(player: Player) {
        player.scoreboard = getManager().getMainScoreboard()
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
        @Getter
        private val lines = arrayOfNulls<Team>(15)
    }
}