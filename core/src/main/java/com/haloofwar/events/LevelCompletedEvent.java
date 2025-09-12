package com.haloofwar.events;

import com.haloofwar.enumerators.LevelType;

public class LevelCompletedEvent {
    private final LevelType levelType;

    public LevelCompletedEvent(LevelType levelType) {
        this.levelType = levelType;
    }

    public LevelType getLevelType() {
        return levelType;
    }
}

