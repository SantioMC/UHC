package me.santio.uhc.utils

import org.spigotmc.event.entity.EntityDismountEvent
import org.bukkit.entity.Player
import me.santio.uhc.utils.FreezeUtils
import org.bukkit.GameMode
import org.spigotmc.event.entity.EntityMountEvent
import java.util.HashMap
import java.util.UUID
import org.bukkit.entity.ArmorStand
import org.bukkit.Bukkit
import org.bukkit.World
import me.santio.uhc.entities.InvisibleArmorStand
import net.md_5.bungee.api.ChatColor

object ChatUtils {
    @JvmStatic
    fun tacc(raw: String?): String {
        return ChatColor.translateAlternateColorCodes('&', raw)
    }
}