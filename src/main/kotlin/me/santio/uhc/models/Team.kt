package me.santio.uhc.models

import lombok.Getter
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
import net.md_5.bungee.api.ChatColor
import org.bukkit.event.HandlerList
import org.apache.commons.lang.WordUtils

enum class Team(@field:Getter private override val name: String, @field:Getter private val color: ChatColor) {
    RED("Red", ChatColor.RED), ORANGE("Orange", ChatColor.GOLD), YELLOW("Yellow", ChatColor.YELLOW), GREEN(
        "Green",
        ChatColor.DARK_GREEN
    ),
    LIME("Lime", ChatColor.GREEN), BLUE("Blue", ChatColor.BLUE), AQUA("Aqua", ChatColor.AQUA), CYAN(
        "Cyan",
        ChatColor.DARK_AQUA
    ),
    PINK("Pink", ChatColor.LIGHT_PURPLE), MAGENTA("Magenta", ChatColor.of("#f653ad")), PURPLE(
        "Purple",
        ChatColor.DARK_PURPLE
    );
}