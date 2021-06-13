package me.santio.uhc.sidebars;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public abstract class Sidebar {
    
//    private final Set<String> EMPTY_LINE = ImmutableSet.of("§f", "§f§r", "§r", "§r§f");
    
    @Getter private final String id;
    @Getter private transient final ScoreboardManager manager = Bukkit.getScoreboardManager();
    @Getter private final static Team[] lines = new Team[15];
    @Getter private transient Scoreboard scoreboard;
    transient private Objective objective;
    
    public Sidebar(String id, String title) {
        this.id = id;
        if (getManager() == null) return;
        scoreboard = getManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective("uhc_"+id, "dummy",
                ChatColor.translateAlternateColorCodes('&', title));
        
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for (int i = 0; i < 15; i++) {
            Team team = getScoreboard().registerNewTeam("uhc_"+id+"_" + i);
            team.addEntry(ChatColor.values()[i].toString() + "§raaaaaaaaa");
            lines[i] = team;
        }
    }
    
    public void apply(Player player) {
        player.setScoreboard(getScoreboard());
    }
    
    public void remove(Player player) {
        player.setScoreboard(getManager().getMainScoreboard());
    }
    
    public void setLine(int line, String text) {
        String colored = ChatColor.translateAlternateColorCodes('&', text);
        Team team = lines[16 - line];
        if (text.length() <= 16) team.setPrefix(colored);
        else {
            team.setPrefix(colored.substring(0, 16));
            team.setSuffix(colored.substring(16));
        }
        team.setPrefix("");
    }
    
}
