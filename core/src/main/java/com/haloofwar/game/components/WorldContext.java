package com.haloofwar.game.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.gameplay.GameplayContext;
import com.haloofwar.ecs.Entity;

public class WorldContext {
	private final GameplayContext gameplay;
	
	// Dependencias
	private final SpriteBatch batch;
	private final GameWorldCamera camera;
	
	public WorldContext(Entity player, MapRenderer map, GameContext context) {
		this.gameplay = context.getGameplay();
		context.getGameplay().initializePlayer(player);
		
		this.camera = context.getWorldCamera();
		this.camera.configure(player, map.getMetaData());
		
		this.batch = context.getRender().getBatch();
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
