package com.haloofwar.game.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.dependences.gameplay.BulletManager;
import com.haloofwar.dependences.gameplay.EntityManager;
import com.haloofwar.entities.characters.Player;

public class WorldContext {
	private final EntityManager entities;
	private final BulletManager bullets;
	private final CollisionManager collisions;
	private final GameWorldCamera camera;
	private final SpriteBatch batch;
	
	public WorldContext(GameContext context, Player player, MapRenderer map) {
		this.entities = context.getGameplay().getEntities();
		this.bullets = context.getGameplay().getBullets();
		this.collisions = context.getCollision();
		this.camera = context.getGameplay().getCamera();
		this.camera.configure(player, map.getMetaData());
		
		this.batch = context.getRender().getBatch();
	}
	
	public EntityManager getEntities() {
		return this.entities;
	}
	
	public BulletManager getBullets() {
		return this.bullets;
	}
	
	public CollisionManager getCollisions() {
		return this.collisions;
	}
	
	public GameWorldCamera getCamera() {
		return this.camera;
	}
	
	public SpriteBatch getBatch() {
		return this.batch;
	}
	
}
