package com.haloofwar.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.core.HaloOfWarPrincipal;
import com.haloofwar.input.InputManager;

public final class Resources {
	private Resources() {}
	
	private static HaloOfWarPrincipal game;
	
	private static int WINDOW_WIDTH = Gdx.graphics.getWidth();
	private static int WINDOW_HEIGHT = Gdx.graphics.getHeight();
	
	private static SpriteBatch batch = new SpriteBatch();
	private static InputManager inputManager = new InputManager();
	private static ShapeRenderer shapeRenderer = new ShapeRenderer();

	
	public static void setGame(HaloOfWarPrincipal game) {
		if (Resources.game == null) {
			Resources.game = game;
			System.out.println("Se ha establecido el juego correctamente.");
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
		batch.dispose();
		if (game != null) {
			game.dispose();
		}
	}
	
	public static int getWINDOW_HEIGHT() {
		return WINDOW_HEIGHT;
	}
	
	public static int getWINDOW_WIDTH() {
		return WINDOW_WIDTH;
	}
	
	public static SpriteBatch getBatch() {
		return batch;
	}
	
	public static void cleanWindow() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	public static InputManager getInputManager() {
		return inputManager;
	}

}
