package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.GameState;

public class GameStateEvent {
    private final GameState state;

    public GameStateEvent(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }
}
