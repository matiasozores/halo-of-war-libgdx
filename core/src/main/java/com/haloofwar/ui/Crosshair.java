package com.haloofwar.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.enumerators.CrosshairType;

public class Crosshair {
    private int mouseX, mouseY;
    private Texture crosshairImage;
    private int width = 16, height = 16;
    
    GameWorldCamera camera;
    
    public Crosshair(CrosshairType type, TextureManager texture, GameWorldCamera camera) {
        this.crosshairImage = texture.get(type);
        this.camera = camera;
    }

    public void update() {
    	Vector3 mouseWorldPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
    	this.camera.getOrthographic().unproject(mouseWorldPos);
		
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