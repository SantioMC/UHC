package me.santio.uhc.scenarios

import me.santio.uhc.models.Scenario.createData
import me.santio.uhc.models.Scenario
import me.santio.uhc.models.ScenarioData
import org.bukkit.Material
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer

class DoubleDropsScenario : Scenario("Double Drops") {
    init {
        setIcon(Material.COAL_ORE)
        createData(ScenarioData("all_blocks", "Should all blocks be affected instead of just ores", false))
    }

    @EventHandler
    fun onMineOre(event: BlockBreakEvent) {
        if (event.isCancelled) return
        if (!getData().get("all_blocks").getValue() && !event.block.type.toString().contains("_ORE")) return
        val world = event.block.world
        event.block.getDrops(event.player.inventory.itemInMainHand, event.player)
            .forEach(Consumer { item: ItemStack? -> world.dropItem(event.block.location, item!!) })
    }
}