package com.haloofwar.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.enumerators.CrosshairType;

public class Crosshair {
    private int mouseX, mouseY;
    private Texture crosshairImage;
    private GameWorldCamera camera;
    private int width = 16, height = 16; 
    
    public Crosshair(CrosshairType type, GameWorldCamera camera, TextureManager texture) {
        this.crosshairImage = texture.get(type);
        this.camera = camera;
        if(this.camera == null) {
        	System.out.println("Se ha ingresado una camara nula");
        }
    }

    public void update() {
    	Vector3 mouseWorldPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
    	this.camera.getOrthographic().unproject(mouseWorldPos);
		
		/* Por ahora no voy a limitar el crosshair
		if (mouseWorldPos.x < 0 || mouseWorldPos.x > camera.getViewportWidth() ||
			mouseWorldPos.y < 0 || mouseWorldPos.y > camera.getViewportHeight()) {
			return;
		}*/
		
		this.mouseX = (int) mouseWorldPos.x;
		this.mouseY = (int) mouseWorldPos.y;
	}
    
    public void render(SpriteBatch batch) {
    	batch.draw(this.crosshairImage, this.mouseX, this.mouseY, this.width, this.height);
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