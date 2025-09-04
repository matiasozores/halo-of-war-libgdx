package com.haloofwar.game.dependences;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.components.Entity;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.GameplayContext;

public class WorldContext {
	private final GameplayContext gameplay;
	
	// Dependencias
	private final SpriteBatch batch;
	private final GameWorldCamera camera;
	private final Entity player;
	private final MapRenderer map;
	
	public WorldContext(Entity player, MapRenderer map, GameContext context) {
		this.gameplay = context.getGameplay();
		this.camera = context.getWorldCamera();
		this.camera.configure(player, map.getMetaData());
		this.batch = context.getRender().getBatch();
		this.player = player;
		this.map = map;
	}
	
	public void reconfigureCamera() {
		this.camera.configure(this.player, this.map.getMetaData());
	}
	
	public GameplayContext getGameplay() {
		return this.gameplay;
	}
	
	public SpriteBatch getBatch() {
		return this.batch;
	}
	
	public GameWorldCamera getCamera() {
		return this.camera;
	}
}
