package com.haloofwar.dependences.collision.types;

import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.dependences.collision.CollisionHandler;

public class EntityObjectCollisionHandler implements CollisionHandler{

	@Override
	public void handle(Collidable a, Collidable b) {
		System.out.println("Jugador colisionando con objeto");
	}

}
