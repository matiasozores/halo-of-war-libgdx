package com.haloofwar.engine.events;

import com.haloofwar.interfaces.SceneKey;

public class ChangeSceneEvent {

    private final SceneKey nextScene;

    public ChangeSceneEvent(SceneKey nextScene) {
        this.nextScene = nextScene;
    }

    public SceneKey getNextScene() {
        return nextScene;
    }
}
