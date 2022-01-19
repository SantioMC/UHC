package me.santio.uhc;

import lombok.Getter;
import lombok.Setter;
import me.santio.uhc.events.GameStartEvent;
import me.santio.uhc.models.GamePlayer;
import me.santio.uhc.models.Scenario;
import me.santio.uhc.utils.ChatUtils;
import me.santio.uhc.utils.FreezeUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Game {
    
    @Setter @Getter private static boolean starting = false;
    @Setter @Getter private static boolean started = false;
    @Setter @Getter private static int countdown = 0;
    @Getter private static final HashMap<UUID, GamePlayer> players = new HashMap<>();
    @Getter private static final HashMap<String, Scenario> allScenarios = new HashMap<>();
    @Getter private static final ArrayList<Scenario> activeScenarios = new ArrayList<>();
    @Getter private static final ArrayList<UUID> alive = new ArrayList<>();
    private static BukkitTask countdowner;
    
    /**
     * Attempts to start the game
     */
    public static void attemptStart() {
        if (isStarting() || isStarted()) return;
        setCountdown(60);
        GameStartEvent startEvent = new GameStartEvent();
        Bukkit.getPluginManager().callEvent(startEvent);
        
        Bukkit.getOnlinePlayers().forEach((Player p) -> {
            alive.add(p.getUniqueId());
            FreezeUtils.freeze(p);
        });
        
        countdowner = new BukkitRunnable() {
            @Override
            public void run() {
                if (!isStarting()) {
                    reset();
                } else if (getCountdown() <= 0) {
                    Game.initiateStart();
                } else {
                    setCountdown(getCountdown() - 1);
                    if (getCountdown() % 5 == 0 || getCountdown() < 5) {
                        Bukkit.broadcastMessage(UHC.getMainColor() + "UHC" + ChatUtils.tacc(" &8| &7The game will begin in ") + UHC.getMainColor() + getCountdown() + " seconds" + ChatUtils.tacc("&7!"));
                    }
                }
            }
        }.runTaskTimer(UHC.getInstance(), 20, 20);
    }
    
    private static void initiateStart() {
        setStarting(false);
        setStarted(true);
        FreezeUtils.reset();
    }
    
    public static void reset() {
        setStarted(false);
        setStarting(false);
        setCountdown(0);
        countdowner.cancel();
        countdowner = null;
        FreezeUtils.reset();
        Bukkit.getOnlinePlayers().forEach((Player player) -> player.teleport(UHC.getLobby()));
    }
    
}
