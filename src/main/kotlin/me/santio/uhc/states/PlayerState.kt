package me.santio.uhc.states

import lombok.Getter
import me.santio.uhc.states.ScoreboardState
import me.santio.uhc.sidebars.Sidebar
import me.santio.uhc.sidebars.LobbySidebar

enum class PlayerState(@field:Getter private val scoreboard: ScoreboardState) {
    LOBBY(ScoreboardState.LOBBY), IN_GAME(ScoreboardState.IN_GAME), DEAD(ScoreboardState.IN_GAME);
}