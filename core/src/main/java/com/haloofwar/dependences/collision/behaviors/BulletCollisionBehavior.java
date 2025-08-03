package com.haloofwar.dependences.collision.behaviors;

import com.haloofwar.entities.Bullet;
import com.haloofwar.entities.Entity;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.statics.Item;
import com.haloofwar.entities.statics.Obstacle;
import com.haloofwar.interfaces.CollisionVisitor;

public class BulletCollisionBehavior implements CollisionVisitor {

	@Override
	public void visit(Bullet bullet, Entity entity) {
		bullet.destroy();
	}

	@Override
	public void visit(LivingEntity living, Entity entity) {
		if (entity instanceof Bullet bullet) {
			living.takeDamage(bullet.getDamage());
		}
	}
	
	@Override
	public void visit(Obstacle obstacle, Entity entity) {
	}

	@Override
	public void visit(Item item, Entity entity) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visit(Entity entity, Entity otherEntity) {
	}

	
}
