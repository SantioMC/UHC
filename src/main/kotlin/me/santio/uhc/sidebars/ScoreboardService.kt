package me.santio.uhc.sidebars

import me.santio.uhc.Game
import me.santio.uhc.models.GamePlayer
import org.bukkit.Bukkit

object ScoreboardService {
    fun lobby(player: GamePlayer) {
        player.board.clear()
        player.board.setAll(
            "§1§7§m                         §7§m",
            "§7Players: §b${Bukkit.getOnlinePlayers().size}",
            "§7Type: §b${Game.getType()}",
            "§7Host: §b${getHost()}",
            "§2§7§m                         §7§m",
            "§bScenarios",
            "§cW.I.P.",
            "§1§f",
            "§3§7§m                         §7§m"
        )
    }

    fun join(player: GamePlayer) {
        player.board.set("§7Players: §b${Bukkit.getOnlinePlayers().size}", 8)
    }

    private fun getHost(): String {
        return if (Game.getHost() == null) "No one"
        else return Game.getHost()!!.name
    }
}