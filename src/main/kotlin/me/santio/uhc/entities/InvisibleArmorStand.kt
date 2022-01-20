package me.santio.uhc.entities

import org.bukkit.entity.ArmorStand
import org.bukkit.util.Consumer

class InvisibleArmorStand : Consumer<ArmorStand> {
    override fun accept(stand: ArmorStand) {
        stand.isVisible = false
        stand.isInvulnerable = true
        stand.isSmall = true
        stand.removeWhenFarAway = true
        stand.setGravity(false)
        stand.isCollidable = false
        stand.isMarker = true
    }
}