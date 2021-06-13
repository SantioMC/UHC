package me.santio.uhc.states;

import lombok.Getter;

public enum PlayerState {
    LOBBY(ScoreboardState.LOBBY),
    IN_GAME(ScoreboardState.IN_GAME),
    DEAD(ScoreboardState.IN_GAME);
    
    @Getter private final ScoreboardState scoreboard;
    PlayerState(ScoreboardState scoreboardState) {
        this.scoreboard = scoreboardState;
    }
}
