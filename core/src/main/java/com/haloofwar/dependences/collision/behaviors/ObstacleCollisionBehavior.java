package com.haloofwar.dependences.collision.behaviors;

import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.Bullet;
import com.haloofwar.entities.Entity;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.statics.Item;
import com.haloofwar.entities.statics.Obstacle;
import com.haloofwar.interfaces.CollisionVisitor;

public class ObstacleCollisionBehavior implements CollisionVisitor {

	@Override
	public void visit(Bullet bullet, Entity entity) {
		bullet.destroy();
	}

    @Override
    public void visit(LivingEntity living, Entity entity) {
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
	public void visit(Obstacle obstacle, Entity entity) {		
	}

	@Override
	public void visit(Entity entity, Entity otherEntity) {		
	}

	@Override
	public void visit(Item item, Entity entity) {
		// TODO Auto-generated method stub
		
	}

}
