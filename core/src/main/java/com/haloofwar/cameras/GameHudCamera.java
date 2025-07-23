package com.haloofwar.cameras;

import com.badlogic.gdx.Gdx;

public class GameHudCamera extends GameCamera {

    public GameHudCamera() {
        super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void update() {
        this.camera.update();
    }
}
