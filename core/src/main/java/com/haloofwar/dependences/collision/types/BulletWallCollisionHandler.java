package com.haloofwar.dependences.collision.types;

import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.dependences.collision.CollisionHandler;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.entities.Bullet;

public class BulletWallCollisionHandler implements CollisionHandler{

	@Override
	public void handle(Collidable a, Collidable b, CollisionManager manager) {
		Bullet bullet = (Bullet) b;
		bullet.destroy();
		manager.remove(bullet);
		
	}
}
