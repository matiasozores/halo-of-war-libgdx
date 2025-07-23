package com.haloofwar.utilities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.core.HaloOfWarPrincipal;
import com.haloofwar.input.InputManager;

public class GameContext {
	private final HaloOfWarPrincipal game;
	private final SpriteBatch batch;
	private final InputManager inputManager;
	private final ShapeRenderer shapeRenderer;
	private GameWorldCamera cameraGame;
	
	public GameContext(HaloOfWarPrincipal game) {
		this.game = game;
		this.batch = new SpriteBatch();
		this.inputManager = new InputManager();
		this.shapeRenderer = new ShapeRenderer();
	}
	
	public void setCameraGame(GameWorldCamera cameraGame) {
		if(this.cameraGame == null) {
			this.cameraGame = cameraGame;
		}
	}
	
	public GameWorldCamera getCameraGame() {
		return this.cameraGame;	
	}
	
	public HaloOfWarPrincipal getGame() {
		return this.game;
	}
	
	public SpriteBatch getBatch() {
		return this.batch;
	}
	
	public InputManager getInputManager() {
		return this.inputManager;
	}
	
	public ShapeRenderer getShapeRenderer() {
		return this.shapeRenderer;
	}

	public void dispose() {
		this.batch.dispose();
		this.shapeRenderer.dispose();	
	}
}
