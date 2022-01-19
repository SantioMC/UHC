package me.santio.uhc.states

import lombok.Getter
import me.santio.uhc.states.ScoreboardState
import me.santio.uhc.sidebars.Sidebar
import me.santio.uhc.sidebars.LobbySidebar

enum class ScoreboardState(@field:Getter private val sidebar: Sidebar?) {
    LOBBY(LobbySidebar()), IN_GAME(null), ADMIN(null);
}