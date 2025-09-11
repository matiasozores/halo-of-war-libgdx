package com.haloofwar.events;

import com.haloofwar.interfaces.Scene;

public class ChangeSceneEvent {

    private final Scene nextScene;

    public ChangeSceneEvent(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public Scene getNextScene() {
        return nextScene;
    }
}
