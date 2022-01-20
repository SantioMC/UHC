package me.santio.uhc.utils

import net.md_5.bungee.api.ChatColor

object ChatUtils {

    fun tacc(raw: String?): String {
        return ChatColor.translateAlternateColorCodes('&', raw)
    }

}