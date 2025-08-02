package com.haloofwar.dependences.gameplay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.interfaces.Renderable;
import com.haloofwar.interfaces.Updatable;

public class GameplayContext implements Renderable, Updatable {
	private final EntityManager entities;
	private final ObjectManager objects;
	private final BulletManager bullets;
	private final CollisionManager collisions;

	public GameplayContext(InputManager input) {
		this.bullets = new BulletManager();
		this.entities = new EntityManager();
		this.objects = new ObjectManager();
		this.collisions = new CollisionManager(input, this.objects); 
	}
	
	@Override
	public void render(SpriteBatch batch) {
		this.entities.render(batch);	
		this.objects.render(batch);
		this.bullets.render(batch);
	}
	
	@Override
	public void update(float delta) {
		this.entities.update(delta);
		this.bullets.update(delta);
		this.collisions.checkCollisions();
	}
	
	
	public BulletManager getBullets() {
		return this.bullets;
	}
	
	public EntityManager getEntities() {
		return this.entities;
	}
	
	public ObjectManager getObjects() {
		return this.objects;
	}
	
	public CollisionManager getCollisions() {
		return this.collisions;
	}
	
	public void dispose() {
		this.bullets.clear();
		this.entities.clear();
	}
}
