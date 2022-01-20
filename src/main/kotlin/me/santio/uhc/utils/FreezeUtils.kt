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
import org.bukkit.entity.Entity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.util.ArrayList
import java.util.function.Consumer

class FreezeUtils : Listener {
    @EventHandler
    fun onDismount(event: EntityDismountEvent) {
        if (event.entity !is Player) return
        val player = event.entity as Player
        if (!frozen.containsKey(player.uniqueId)) return
        event.isCancelled = true
        if (player.gameMode == GameMode.CREATIVE) thaw(player)
    }

    @EventHandler
    fun onMount(event: EntityMountEvent) {
        if (event.entity !is Player) return
        val player = event.entity as Player
        if (frozen.containsKey(player.uniqueId)) event.isCancelled = true
    }

    companion object {
        private val frozen = HashMap<UUID, ArmorStand>()
        private val armorStands: List<Entity>
            private get() {
                val armorStands: MutableList<Entity> = ArrayList()
                Bukkit.getWorlds().forEach(Consumer { w: World ->
                    w.entities.stream().filter { e: Entity -> e.scoreboardTags.contains("freeze") }
                        .forEach { e: Entity -> armorStands.add(e) }
                })
                return armorStands
            }

        fun freeze(player: Player) {
            if (frozen.containsKey(player.uniqueId) || player.gameMode == GameMode.SPECTATOR) return
            val loc = player.location
            val armorStand = player.world.spawn(loc.add(0.0, 0.5, 0.0), ArmorStand::class.java, InvisibleArmorStand())
            armorStand.addScoreboardTag("freeze")
            armorStand.addPassenger(player)
            frozen[player.uniqueId] = armorStand
        }

        fun thaw(player: Player) {
            if (frozen.containsKey(player.uniqueId)) {
                frozen[player.uniqueId]!!.remove()
                frozen.remove(player.uniqueId)
            }
        }

        fun reset() {
            armorStands.forEach(Consumer { obj: Entity -> obj.remove() })
            frozen.clear()
        }
    }
}