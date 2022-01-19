package me.santio.uhc.models;

import lombok.Getter;
import lombok.Setter;
import me.santio.uhc.UHC;
import me.santio.uhc.states.PlayerState;
import me.santio.uhc.states.ScoreboardState;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@SuppressWarnings("unused")
public class GamePlayer {
    
    @Setter @Getter private PlayerState playerState = PlayerState.LOBBY;
    @Setter @Getter private ScoreboardState scoreboardState = ScoreboardState.LOBBY;
    @Setter @Getter private Team team = Team.MAGENTA;
    @Setter @Getter private int kills = 0;
    @Setter @Getter private String kit = "None";
    @Getter private final UUID player;
    
    public GamePlayer(UUID uniqueId) {
        this.player = uniqueId;
    }
    
    /**
     * Fetches the Player object from this context.
     *
     * @return The player, or null if they are not online
     */
    public Player fetchPlayer() {
        return Bukkit.getPlayer(getPlayer());
    }
    
    /**
     * Get the multi-lines hover message to show in the chat based
     * on their current state.
     *
     * @return The multi-lined BaseComponent message
     */
    public BaseComponent[] getChatHover() {
        return getPlayerState().equals(PlayerState.LOBBY) ?
                new BaseComponent[]{new TextComponent(""),
                    new TextComponent("\n" + ChatColor.GRAY + "Team: " + getTeam().getColor() + getTeam().getName()),
                    new TextComponent("\n" + ChatColor.GRAY + "Selected kit: " + UHC.getMainColor() + getKit()),
                    new TextComponent("\n")} :
                new BaseComponent[]{new TextComponent(""),
                    new TextComponent("\n" + ChatColor.GRAY + "Team: " + getTeam().getColor() + getTeam().getName()),
                    new TextComponent("\n" + ChatColor.GRAY + "Selected kit: " + UHC.getMainColor() + getKit()),
                    new TextComponent("\n" + ChatColor.GRAY + "Kills: " + UHC.getMainColor() + getKit()),
                    new TextComponent("\n")};
    }
}
