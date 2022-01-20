package me.santio.uhc.models

import net.md_5.bungee.api.ChatColor

enum class Team(
    val teamName: String,
    val color: ChatColor
) {
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