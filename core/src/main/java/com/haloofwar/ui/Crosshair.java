package com.haloofwar.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.utilities.Image;

public class Crosshair {
    private int mouseX, mouseY;
    private Image crosshairImage;
    private GameWorldCamera camera;
    
    public Crosshair(String path, GameWorldCamera camera) {
        this.crosshairImage = new Image(path, 10, 10);
        this.camera = camera;
        if(this.camera == null) {
        	System.out.println("Se ha ingresado una camara nula");
        }
    }

    public void update() {
    	Vector3 mouseWorldPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
    	this.camera.getCamera().unproject(mouseWorldPos);
		
		/* Por ahora no voy a limitar el crosshair
		if (mouseWorldPos.x < 0 || mouseWorldPos.x > camera.getViewportWidth() ||
			mouseWorldPos.y < 0 || mouseWorldPos.y > camera.getViewportHeight()) {
			return;
		}*/
		
		this.mouseX = (int) mouseWorldPos.x;
		this.mouseY = (int) mouseWorldPos.y;
	}
    
    public void render(SpriteBatch batch) {
    	this.crosshairImage.render(batch, this.mouseX, this.mouseY);
    }

    public void dispose() {
    	this.crosshairImage.dispose();
    }
    
    public int getMouseX() {
		return this.mouseX;
	}
    
    public int getMouseY() {
		return this.mouseY;
	}
}
