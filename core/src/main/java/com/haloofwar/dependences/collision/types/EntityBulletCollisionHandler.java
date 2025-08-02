package com.haloofwar.dependences.collision.types;

import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.dependences.collision.CollisionHandler;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.weapons.guns.Bullet;

public class EntityBulletCollisionHandler implements CollisionHandler{

	@Override
	public void handle(Collidable a, Collidable b, CollisionManager manager) {
		System.out.println("Entra a esta colision");
	    if (!(a instanceof LivingEntity) || !(b instanceof Bullet)) {
	        return; 
	    }
	    
	    LivingEntity player = (LivingEntity) a;
	    Bullet bullet = (Bullet) b;
	    
	    bullet.destroy();
	    manager.removeCollidable(bullet);
	    
	    player.takeDamage(bullet.getDamage()); 

	    if(!player.isAlive()) {
	    	manager.removeCollidable(player);
	    }
	    
	   
	}

	
}
