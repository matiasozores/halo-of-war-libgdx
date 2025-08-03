package com.haloofwar.entities.components;

import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.dependences.gameplay.EntityManager;
import com.haloofwar.entities.Entity;
import com.haloofwar.interfaces.StateHandler;

public class EntityStateHandler implements StateHandler {

	private final CollisionManager collision;
	private final EntityManager entities;
	
	public EntityStateHandler(CollisionManager collision, EntityManager entities) {
		this.collision = collision;
		this.entities = entities;
	}
	
	@Override
	public void onDeath(Entity entity) {
		this.collision.remove(entity);
		this.entities.remove(entity);
	}

}
