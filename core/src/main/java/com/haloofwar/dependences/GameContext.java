package com.haloofwar.dependences;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.audio.MusicManager;
import com.haloofwar.audio.SoundManager;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.collision.CollisionManager;
import com.haloofwar.core.HaloOfWarPrincipal;

public class GameContext {
	private final HaloOfWarPrincipal game;
	private final SpriteBatch batch;
	private final InputManager inputManager;
	private final ShapeRenderer shapeRenderer;
	private final MusicManager musicManager;
	private final SoundManager soundManager;
	private final FontManager fontManager;
	private final GameWorldCamera cameraGame;
	private final BulletManager bulletManager;
	private final TextureManager textureManager;
	private final CollisionManager collisionManager;
	
	public GameContext(HaloOfWarPrincipal game) {
		this.game = game;
		this.batch = new SpriteBatch();
		this.inputManager = new InputManager();
		this.shapeRenderer = new ShapeRenderer();
		this.musicManager = new MusicManager();
		this.soundManager = new SoundManager();
		this.fontManager = new FontManager();
		this.cameraGame = new GameWorldCamera();
		this.bulletManager = new BulletManager();
		this.textureManager = new TextureManager();
		this.collisionManager = new CollisionManager();
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
	
	public SoundManager getSoundManager() {
		return this.soundManager;
	}
	
	public MusicManager getMusicManager() {
		return this.musicManager;
	}
	
	public FontManager getFontManager() {
		return this.fontManager;
	}
	
	public BulletManager getBulletManager() {
		return this.bulletManager;
	}
	
	public TextureManager getTextureManager() {
		return this.textureManager;
	}
	
	public CollisionManager getCollisionManager() {
		return this.collisionManager;
	}
	
	public void dispose() {
		this.batch.dispose();
		this.shapeRenderer.dispose();	
	}
}
