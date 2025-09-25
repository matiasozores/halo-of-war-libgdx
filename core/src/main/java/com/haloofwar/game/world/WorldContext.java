package com.haloofwar.game.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.context.GameplayContext;
import com.haloofwar.engine.cameras.GameWorldCamera;
import com.haloofwar.engine.entity.Entity;

public class WorldContext {
	private GameplayContext GAMEPLAY;
	
	// Dependencias
	private final SpriteBatch BATCH;
	private final GameWorldCamera WORLD_CAMERA;
	private final Entity PLAYER;
	private final MapRenderer MAP;
	
	public WorldContext(final MapRenderer MAP,
			final GameplayContext GAMEPLAY,
			final SpriteBatch BATCH,
			final GameWorldCamera WORLD_CAMERA) {

		this.GAMEPLAY = GAMEPLAY;
		this.PLAYER = this.GAMEPLAY.getCurrentPlayer();
		this.BATCH = BATCH;
		this.WORLD_CAMERA = WORLD_CAMERA;
		this.WORLD_CAMERA.configure(PLAYER, MAP.getMetaData());
		this.MAP = MAP;
	}
	
	public void reconfigureCamera() {
		this.WORLD_CAMERA.configure(this.PLAYER, this.MAP.getMetaData());
	}
	
	public SpriteBatch getBatch() {
		return this.BATCH;
	}
	
	public GameWorldCamera getCamera() {
		return this.WORLD_CAMERA;
	}
	
	public GameplayContext getGameplay() {
		return this.GAMEPLAY;
	}
}
