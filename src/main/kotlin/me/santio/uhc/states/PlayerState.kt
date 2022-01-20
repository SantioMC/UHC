package me.santio.uhc.states

enum class PlayerState(val scoreboard: ScoreboardState) {
    LOBBY(ScoreboardState.LOBBY), IN_GAME(ScoreboardState.IN_GAME), DEAD(ScoreboardState.IN_GAME);
}