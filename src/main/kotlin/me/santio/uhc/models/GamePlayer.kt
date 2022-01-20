package me.santio.uhc.models

import me.santio.uhc.UHC
import me.santio.uhc.states.PlayerState
import me.santio.uhc.states.ScoreboardState
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

class GamePlayer(private val player: UUID) {
    var playerState = PlayerState.LOBBY
    var scoreboardState = ScoreboardState.LOBBY
    var team = Team.MAGENTA
    var kills = 0
    var kit = "None"

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