package com.haloofwar.cameras;

import com.haloofwar.utilities.GameConfig;

public class GameStaticCamera extends GameCamera {

    public GameStaticCamera() {
        super(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
    }

    @Override
    public void update() {
        this.camera.update();
    }
}
