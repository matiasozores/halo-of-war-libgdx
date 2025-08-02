package com.haloofwar.dependences.collision.types;

import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.dependences.collision.CollisionHandler;
import com.haloofwar.dependences.collision.CollisionManager;

public class EntityObjectCollisionHandler implements CollisionHandler{

	@Override
	public void handle(Collidable a, Collidable b, CollisionManager manager) {
		System.out.println("Jugador colisionando con objeto");
	}

}
