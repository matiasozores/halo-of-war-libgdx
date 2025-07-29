package com.haloofwar.collision.types;

import com.haloofwar.collision.Collidable;
import com.haloofwar.collision.CollisionHandler;
import com.haloofwar.entities.Entity;
import com.haloofwar.weapons.guns.Bullet;

public class EntityBulletCollisionHandler implements CollisionHandler{

	@Override
	public void handle(Collidable a, Collidable b) {
	    if (!(a instanceof Entity) || !(b instanceof Bullet)) {
	        return; 
	    }
	    
	    Entity player = (Entity) a;
	    Bullet bullet = (Bullet) b;
	    
	    player.takeDamage(bullet.getDamage()); 

	    bullet.destroy();
	}

	
}
