package com.haloofwar.dependences.collision.types;

import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.dependences.collision.CollisionHandler;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.entities.Bullet;
import com.haloofwar.entities.LivingEntity;

public class EntityBulletCollisionHandler implements CollisionHandler{

	@Override
	public void handle(Collidable a, Collidable b, CollisionManager manager) {
	    if (!(a instanceof LivingEntity) || !(b instanceof Bullet)) {
	        return; 
	    }
	    
	    LivingEntity player = (LivingEntity) a;
	    Bullet bullet = (Bullet) b;
	    
	    bullet.destroy();
	    manager.remove(bullet);
	    
	    player.takeDamage(bullet.getDamage()); 

	    if(!player.isAlive()) {
	    	manager.remove(player);
	    }
	    
	   
	}

	
}
