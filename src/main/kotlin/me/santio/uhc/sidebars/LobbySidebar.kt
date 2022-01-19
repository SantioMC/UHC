package me.santio.uhc.sidebars

import me.santio.uhc.sidebars.Sidebar
import me.santio.uhc.UHC
import org.bukkit.scoreboard.ScoreboardManager
import org.bukkit.Bukkit
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.entity.Player

class LobbySidebar : Sidebar("lobby", UHC.getMainColor().toString() + "UHC &8| &7Lobby") {
    init {
        setLine(1, "Test :)")
        setLine(3, "Hey!")
    }
}