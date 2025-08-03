package com.haloofwar.dependences.collision.behaviors;

import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.Bullet;
import com.haloofwar.entities.Entity;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.statics.Obstacle;
import com.haloofwar.entities.statics.items.Item;
import com.haloofwar.enumerators.entities.behavior.CollisionType;
import com.haloofwar.interfaces.Collidable;
import com.haloofwar.interfaces.CollisionVisitor;

public class ObstacleCollisionBehavior implements CollisionVisitor {

	@Override
	public void visit(Bullet bullet, Collidable entity) {
		bullet.destroy();
	}

    @Override
    public void visit(LivingEntity living, Collidable entity) {
        MovementComponent movement = living.getMovement();

        float currentX = movement.getX();
        float currentY = movement.getY();
        float lastX = movement.getLastX();
        float lastY = movement.getLastY();

        movement.setX(lastX);
        movement.setY(currentY);
        living.setPosition(lastX, currentY);
        if (!living.getBounds().overlaps(entity.getBounds())) {
            return;
        }

        movement.setX(currentX);
        movement.setY(lastY);
        living.setPosition(currentX, lastY);
        if (!living.getBounds().overlaps(entity.getBounds())) {
            return; 
        }

        movement.setX(lastX);
        movement.setY(lastY);
        living.setPosition(lastX, lastY);
    }

	@Override
	public void visit(Obstacle obstacle, Collidable entity) {		
	}

	@Override
	public void visit(Entity entity, Collidable otherEntity) {		
	}

	@Override
	public void visit(Item item, Collidable entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CollisionType getCollisionType() {
		return CollisionType.OBSTACLE;
	}

}
