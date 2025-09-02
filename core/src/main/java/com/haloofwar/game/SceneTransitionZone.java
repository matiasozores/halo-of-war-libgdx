package com.haloofwar.game;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.enumerators.SceneType;

public class SceneTransitionZone {
	private final Rectangle bounds;
	private final SceneType targetScene;
	
    public SceneTransitionZone(float x, float y, float width, float height, SceneType targetScene) {
        this.bounds = new Rectangle(x, y, width, height);
        this.targetScene = targetScene;
    }

    public boolean isPlayerInside(float playerX, float playerY) {
        return bounds.contains(playerX, playerY);
    }

    public SceneType getTargetScene() {
        return targetScene;
    }
	
}
