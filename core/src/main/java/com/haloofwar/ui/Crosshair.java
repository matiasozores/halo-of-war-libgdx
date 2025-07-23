package com.haloofwar.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.utilities.Image;

public class Crosshair {
    private int mouseX, mouseY;
    private Image crosshairImage;
    
    public Crosshair(String path) {
        this.crosshairImage = new Image(path, 10, 10);
    }

    public void update(GameWorldCamera camera) {
    	Vector3 mouseWorldPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.getCamera().unproject(mouseWorldPos);
		
		
		if (mouseWorldPos.x < 0 || mouseWorldPos.x > camera.getViewportWidth() ||
			mouseWorldPos.y < 0 || mouseWorldPos.y > camera.getViewportHeight()) {
			return;
		}
		
		this.mouseX = (int) mouseWorldPos.x;
		this.mouseY = (int) mouseWorldPos.y;
	}
    
    public void render(SpriteBatch batch) {
    	this.crosshairImage.render(batch, this.mouseX, this.mouseY);
    }

    public void dispose() {
    	this.crosshairImage.dispose();
    }
}
