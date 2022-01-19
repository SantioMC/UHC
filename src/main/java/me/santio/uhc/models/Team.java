package me.santio.uhc.models;

import net.md_5.bungee.api.ChatColor;
import lombok.Getter;

public enum Team {
    RED("Red", ChatColor.RED),
    ORANGE("Orange", ChatColor.GOLD),
    YELLOW("Yellow", ChatColor.YELLOW),
    GREEN("Green", ChatColor.DARK_GREEN),
    LIME("Lime", ChatColor.GREEN),
    BLUE("Blue", ChatColor.BLUE),
    AQUA("Aqua", ChatColor.AQUA),
    CYAN("Cyan", ChatColor.DARK_AQUA),
    PINK("Pink", ChatColor.LIGHT_PURPLE),
    MAGENTA("Magenta", ChatColor.of("#f653ad")),
    PURPLE("Purple", ChatColor.DARK_PURPLE);
    
    @Getter private final String name;
    @Getter private final ChatColor color;
    Team(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }
}
