package com.haloofwar.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class GameCamera {
    protected OrthographicCamera camera;
    protected Viewport viewport;
    
    public GameCamera(float viewportWidth, float viewportHeight) {
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(viewportWidth, viewportHeight, this.camera);
        this.viewport.apply();
        this.camera.setToOrtho(false, viewportWidth, viewportHeight);
        this.camera.update();
    }

    public OrthographicCamera getOrthographic() {
        return this.camera;
    }
    
    public Viewport getViewport() {
		return this.viewport;
	}
    
    public int getViewportWidth() {
		return (int) this.viewport.getWorldWidth();
	}
    
    public int getViewportHeight() {
    	return (int) this.viewport.getWorldHeight();
    }

    public void resize(int width, int height) {
        this.viewport.update(width, height);
    }

    public abstract void update();
}
