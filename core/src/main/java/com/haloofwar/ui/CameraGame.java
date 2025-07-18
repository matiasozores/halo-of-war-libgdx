package com.haloofwar.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.world.MapMetaData;

public class CameraGame {
	private OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	
	public CameraGame() {
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.update();
	}
	
	public void update(Player player1, MapMetaData mapMetaData) {
		this.camera.zoom = 0.4f;

		float halfViewportWidth = this.camera.viewportWidth * this.camera.zoom / 2;
		float halfViewportHeight = this.camera.viewportHeight * this.camera.zoom / 2;

		// Posición deseada de la cámara
		float camX = player1.getX();
		float camY = player1.getY();

		// Clamp en X
		camX = Math.max(camX, halfViewportWidth);
		camX = Math.min(camX, mapMetaData.getMapPixelWidth() - halfViewportWidth);

		// Clamp en Y
		camY = Math.max(camY, halfViewportHeight);
		camY = Math.min(camY, mapMetaData.getMapPixelHeight() - halfViewportHeight);

		this.camera.position.set(camX, camY, 0);
		this.camera.update();
	}
	
	public OrthographicCamera getCamera() {
		return this.camera;
	}
	
	public void resize(int width, int height) {
		this.camera.viewportWidth = width;
		this.camera.viewportHeight = height;
	}
}
