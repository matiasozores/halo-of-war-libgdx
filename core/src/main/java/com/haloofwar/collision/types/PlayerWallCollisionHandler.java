package com.haloofwar.collision.types;

import com.haloofwar.collision.Collidable;
import com.haloofwar.collision.CollisionHandler;

public class PlayerWallCollisionHandler implements CollisionHandler{

	@Override
	public void handle(Collidable a, Collidable b) {
		System.out.println("Colisionando jugador con pared");
	}

}
