package com.haloofwar.dependences.gameplay;

import com.haloofwar.cameras.GameWorldCamera;

public class GameplayContext {
	private final BulletManager bullets;
	private final EntityManager entities;
	private final GameWorldCamera camera;
	
	public GameplayContext() {
		this.bullets = new BulletManager();
		this.entities = new EntityManager();
		this.camera = new GameWorldCamera();
	}
	
	public BulletManager getBullets() {
		return this.bullets;
	}
	
	public EntityManager getEntities() {
		return this.entities;
	}
	
	public GameWorldCamera getCamera() {
		return this.camera;
	}
	
	public void dispose() {
		this.bullets.clear();
		this.entities.clear();
	}
}
