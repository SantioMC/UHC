package me.santio.uhc.scenarios;

import me.santio.uhc.exceptions.DuplicateScenarioException;
import me.santio.uhc.models.Scenario;
import me.santio.uhc.models.ScenarioData;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class DoubleDropsScenario extends Scenario {
    public DoubleDropsScenario() throws DuplicateScenarioException {
        super("Double Drops");
        setIcon(Material.COAL_ORE);
        createData(new ScenarioData("all_blocks", "Should all blocks be affected instead of just ores", false));
    }
    
    @EventHandler
    public void onMineOre(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        if (!(boolean) getData().get("all_blocks").getValue() && !event.getBlock().getType().toString().contains("_ORE")) return;
        
        World world = event.getBlock().getWorld();
        event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand(), event.getPlayer()).forEach((ItemStack item) -> world.dropItem(event.getBlock().getLocation(), item));
    }
}
