package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.LevelSceneType;

public class LevelCompletedEvent {
    private final LevelSceneType levelType;
    public boolean showCompletedScene;
    
    public LevelCompletedEvent(LevelSceneType levelType) {
        this.levelType = levelType;
        this.showCompletedScene = true;
    }
    
    public LevelCompletedEvent(LevelSceneType levelType, boolean showCompletedScene) {
        this.levelType = levelType;
        this.showCompletedScene = showCompletedScene;
    }
    
    public LevelSceneType getLevelType() {
        return levelType;
    }
}

