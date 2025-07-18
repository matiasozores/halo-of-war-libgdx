package com.haloofwar.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.core.HaloOfWarPrincipal;
import com.haloofwar.input.InputManager;
import com.haloofwar.ui.CameraGame;

public final class Resources {
	private Resources() {}
	
	private static HaloOfWarPrincipal game;
	
	
	private static SpriteBatch batchUI = new SpriteBatch();
	private static SpriteBatch batch = new SpriteBatch();
	private static SpriteBatch batchHUD = new SpriteBatch();
	private static InputManager inputManager = new InputManager();
	private static ShapeRenderer shapeRenderer = new ShapeRenderer();
	private static CameraGame cameraGame = new CameraGame();
	private static int mouseX, mouseY;
	
	public static void setGame(HaloOfWarPrincipal game) {
		if (Resources.game == null) {
			Resources.game = game;
		}
	}
	
	public static HaloOfWarPrincipal getGame() {
		return game;
	}
	
	public static ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}
	
	public static void dispose() {
		shapeRenderer.dispose();
		batchUI.dispose();
		if (game != null) {
			game.dispose();
		}
	}
	
	public static SpriteBatch getBatchUI() {
		return batchUI;
	}
	
	public static SpriteBatch getBatchHUD() {
		return batchHUD;
	}
	
	public static SpriteBatch getBatch() {
		return batch;
	}
	
	public static InputManager getInputManager() {
		return inputManager;
	}
	
	public static CameraGame getCameraGame() {
		return cameraGame;
	}
	
	public static int getMouseX() {
		return mouseX;
	}
	
	public static int getMouseY() {
		return mouseY;
	}
	
	public static void setMousePosition(int x, int y) {
		mouseX = x;
		mouseY = y;
	}
	
	public static void cleanWindow() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	
	

}
