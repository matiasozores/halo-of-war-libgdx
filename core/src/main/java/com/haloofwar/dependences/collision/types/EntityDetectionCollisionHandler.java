package com.haloofwar.dependences.collision.types;

import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.dependences.collision.CollisionHandler;
import com.haloofwar.dependences.collision.CollisionManager;

public class EntityDetectionCollisionHandler implements CollisionHandler {

	@Override
	public void handle(Collidable a, Collidable b, CollisionManager manager) {
		System.out.println("Colision detected between " + a.getClass().getSimpleName() + " and " + b.getClass().getSimpleName());
	}
	
}
