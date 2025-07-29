package com.haloofwar.collision.types;

import com.haloofwar.collision.Collidable;
import com.haloofwar.collision.CollisionHandler;
import com.haloofwar.weapons.guns.Bullet;

public class BulletWallCollisionHandler implements CollisionHandler{

	@Override
	public void handle(Collidable a, Collidable b) {
		Bullet bullet = (Bullet) b;
		bullet.destroy();
	}

}
