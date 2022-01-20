package me.santio.uhc.sidebars

import me.santio.uhc.UHC

class LobbySidebar : Sidebar("lobby", "${UHC.mainColor}UHC &8| &7Lobby") {
    init {
        setLine(1, "Test :)")
        setLine(3, "Hey!")
    }
}