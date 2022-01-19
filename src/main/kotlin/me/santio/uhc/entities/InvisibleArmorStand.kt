package me.santio.uhc.entities

import org.bukkit.entity.ArmorStand
import org.bukkit.util.Consumer

class InvisibleArmorStand : Consumer<ArmorStand> {
    override fun accept(`as`: ArmorStand) {
        `as`.isVisible = false
        `as`.isInvulnerable = true
        `as`.isSmall = true
        `as`.removeWhenFarAway = true
        `as`.setGravity(false)
        `as`.isCollidable = false
        `as`.isMarker = true
    }
}