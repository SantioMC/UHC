package me.santio.uhc.models

import lombok.Getter
import lombok.Setter
import java.util.UUID
import me.santio.uhc.states.ScoreboardState
import org.bukkit.entity.Player
import org.bukkit.Bukkit
import net.md_5.bungee.api.chat.BaseComponent
import me.santio.uhc.UHC
import java.util.HashMap
import me.santio.uhc.models.ScenarioData
import java.util.Locale
import me.santio.uhc.Game
import me.santio.uhc.exceptions.DuplicateScenarioException
import me.santio.uhc.states.PlayerState
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.event.HandlerList
import org.apache.commons.lang.WordUtils

class GamePlayer(@field:Getter private val player: UUID) {
    @Setter
    @Getter
    private val playerState = PlayerState.LOBBY

    @Setter
    @Getter
    private val scoreboardState = ScoreboardState.LOBBY

    @Setter
    @Getter
    private val team = Team.MAGENTA

    @Setter
    @Getter
    private val kills = 0

    @Setter
    @Getter
    private val kit = "None"

    /**
     * Fetches the Player object from this context.
     *
     * @return The player, or null if they are not online
     */
    fun fetchPlayer(): Player {
        return Bukkit.getPlayer(getPlayer())
    }

    /**
     * Get the multi-lines hover message to show in the chat based
     * on their current state.
     *
     * @return The multi-lined BaseComponent message
     */
    val chatHover: Array<BaseComponent>
        get() = if (getPlayerState() == PlayerState.LOBBY) arrayOf(
            TextComponent(""),
            TextComponent(
                """
    
    ${ChatColor.GRAY}Team: ${getTeam().getColor()}${getTeam().getName()}
    """.trimIndent()
            ),
            TextComponent(
                """
    
    ${ChatColor.GRAY}Selected kit: ${UHC.getMainColor()}${getKit()}
    """.trimIndent()
            ),
            TextComponent("\n")
        ) else arrayOf(
            TextComponent(""),
            TextComponent(
                """
    
    ${ChatColor.GRAY}Team: ${getTeam().getColor()}${getTeam().getName()}
    """.trimIndent()
            ),
            TextComponent(
                """
    
    ${ChatColor.GRAY}Selected kit: ${UHC.getMainColor()}${getKit()}
    """.trimIndent()
            ),
            TextComponent(
                """
    
    ${ChatColor.GRAY}Kills: ${UHC.getMainColor()}${getKit()}
    """.trimIndent()
            ),
            TextComponent("\n")
        )
}