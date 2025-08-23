package com.haloofwar.events;

import com.haloofwar.enumerators.GameState;

public class GameStateEvent {
    private final GameState state;

    public GameStateEvent(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }
}
