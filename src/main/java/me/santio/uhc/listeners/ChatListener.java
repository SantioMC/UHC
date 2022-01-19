package me.santio.uhc.listeners;

import me.santio.uhc.Game;
import me.santio.uhc.UHC;
import me.santio.uhc.models.GamePlayer;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    
    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        if (!event.isCancelled()) {
            GamePlayer gamePlayer = Game.getPlayers().get(event.getPlayer().getUniqueId());
            if (gamePlayer == null) return;
    
            HoverEvent hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, gamePlayer.getChatHover());
            BaseComponent[] component = TextComponent.fromLegacyText(event.getFormat());
            component[0].setHoverEvent(hoverEvent);
            
            event.setCancelled(true);
            Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(component));
        }
    }
    
}
