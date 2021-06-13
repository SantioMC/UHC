package me.santio.uhc.sidebars;

import me.santio.uhc.UHC;

public class LobbySidebar extends Sidebar {
    
    public LobbySidebar() {
        super("lobby", UHC.getMainColor()+"UHC &8| &7Lobby");
        this.setLine(1, "Test :)");
        this.setLine(3, "Hey!");
    }

}
