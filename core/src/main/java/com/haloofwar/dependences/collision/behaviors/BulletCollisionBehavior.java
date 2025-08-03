package com.haloofwar.dependences.collision.behaviors;

import com.haloofwar.entities.Bullet;
import com.haloofwar.entities.Entity;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.statics.Obstacle;
import com.haloofwar.entities.statics.items.Item;
import com.haloofwar.enumerators.entities.behavior.CollisionType;
import com.haloofwar.interfaces.Collidable;
import com.haloofwar.interfaces.CollisionVisitor;

public class BulletCollisionBehavior implements CollisionVisitor {

	@Override
	public void visit(Bullet bullet, Collidable entity) {
		bullet.destroy();
	}

	@Override
	public void visit(LivingEntity living, Collidable entity) {
		if (entity instanceof Bullet bullet) {
			living.takeDamage(bullet.getDamage());
		}
	}
	
	@Override
	public void visit(Obstacle obstacle, Collidable entity) {
	}

	@Override
	public void visit(Item item, Collidable entity) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void visit(Entity entity, Collidable otherEntity) {
	}

	@Override
	public CollisionType getCollisionType() {
		return CollisionType.BULLET;
	}	
}
