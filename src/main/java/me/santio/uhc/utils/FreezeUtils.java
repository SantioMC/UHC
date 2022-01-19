package me.santio.uhc.utils;

import me.santio.uhc.entities.InvisibleArmorStand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FreezeUtils implements Listener {
    
    private static final HashMap<UUID, ArmorStand> frozen = new HashMap<>();
    
    private static List<Entity> getArmorStands() {
        List<Entity> armorStands = new ArrayList<>();
        Bukkit.getWorlds().forEach((World w) -> w.getEntities().stream().filter((Entity e) -> e.getScoreboardTags().contains("freeze")).forEach(armorStands::add));
        return armorStands;
    }
    
    public static void freeze(Player player) {
        if (frozen.containsKey(player.getUniqueId()) || player.getGameMode().equals(GameMode.SPECTATOR)) return;
        Location loc = player.getLocation();
        ArmorStand armorStand = player.getWorld().spawn(loc.add(0, 0.5, 0), ArmorStand.class, new InvisibleArmorStand());
        armorStand.addScoreboardTag("freeze");
        armorStand.addPassenger(player);
        frozen.put(player.getUniqueId(), armorStand);
    }
    
    public static void thaw(Player player) {
        if (frozen.containsKey(player.getUniqueId())) {
            frozen.get(player.getUniqueId()).remove();
            frozen.remove(player.getUniqueId());
        }
    }
    
    public static void reset() {
        getArmorStands().forEach(Entity::remove);
        frozen.clear();
    }
    
    @EventHandler
    public void onDismount(EntityDismountEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (!frozen.containsKey(player.getUniqueId())) return;
        event.setCancelled(true);
        if (player.getGameMode().equals(GameMode.CREATIVE)) thaw(player);
    }
    
    @EventHandler
    public void onMount(EntityMountEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (frozen.containsKey(player.getUniqueId())) event.setCancelled(true);
    }
    
}
