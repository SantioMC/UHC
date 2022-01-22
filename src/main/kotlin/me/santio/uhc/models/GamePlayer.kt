package me.santio.uhc.models

import fr.minuskube.netherboard.Netherboard
import fr.minuskube.netherboard.bukkit.BPlayerBoard
import me.santio.uhc.UHC
import me.santio.uhc.sidebars.ScoreboardService
import me.santio.uhc.states.PlayerState
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

class GamePlayer(private val player: UUID) {
    var playerState = PlayerState.LOBBY
    var team = Team.MAGENTA
    var kills = 0
    var kit = "None"
    var board: BPlayerBoard

    init {
        board = Netherboard.instance().createBoard(getPlayer(), "${UHC.mainColor}§lUHC")
        ScoreboardService.lobby(this)
    }

    fun getPlayer(): Player? {
        return Bukkit.getPlayer(player)
    }

    /**
     * Get the multi-lines hover message to show in the chat based
     * on their current state.
     *
     * @return The multi-lined BaseComponent message
     */
    val chatHover: Array<BaseComponent>
        get() = if (playerState == PlayerState.LOBBY) arrayOf(
            TextComponent(""),
            TextComponent(
                """
    
    ${ChatColor.GRAY}Team: ${team.color}${team.teamName}
    """.trimIndent()
            ),
            TextComponent(
                """
    
    ${ChatColor.GRAY}Selected kit: ${UHC.mainColor}${kit}
    """.trimIndent()
            ),
            TextComponent("\n")
        ) else arrayOf(
            TextComponent(""),
            TextComponent(
                """
    
    ${ChatColor.GRAY}Team: ${team.color}${team.teamName}
    """.trimIndent()
            ),
            TextComponent(
                """
    
    ${ChatColor.GRAY}Selected kit: ${UHC.mainColor}${kit}
    """.trimIndent()
            ),
            TextComponent(
                """
    
    ${ChatColor.GRAY}Kills: ${UHC.mainColor}${kills}
    """.trimIndent()
            ),
            TextComponent("\n")
        )
}