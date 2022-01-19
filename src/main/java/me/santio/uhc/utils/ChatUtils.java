package me.santio.uhc.utils;

import net.md_5.bungee.api.ChatColor;

public class ChatUtils {
    
    public static String tacc(String raw) {
        return ChatColor.translateAlternateColorCodes('&', raw);
    }
    
}
