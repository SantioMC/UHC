package me.santio.uhc.states

import me.santio.uhc.sidebars.LobbySidebar
import me.santio.uhc.sidebars.Sidebar

enum class ScoreboardState(val sidebar: Sidebar?) {
    LOBBY(LobbySidebar()), IN_GAME(null), ADMIN(null);
}