package com.haloofwar.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class GameCamera {
    protected OrthographicCamera camera;

    public GameCamera(float viewportWidth, float viewportHeight) {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, viewportWidth, viewportHeight);
        this.camera.update();
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }
    
    public int getViewportWidth() {
		return (int) this.camera.viewportWidth;
	}
    
    public int getViewportHeight() {
    	return (int) this.camera.viewportHeight;
    }

    public void resize(int width, int height) {
        this.camera.viewportWidth = width;
        this.camera.viewportHeight = height;
        this.camera.update();
    }

    public abstract void update();
}
