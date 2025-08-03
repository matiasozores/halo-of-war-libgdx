package com.haloofwar.dependences.collision.behaviors;

import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.Bullet;
import com.haloofwar.entities.Entity;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.statics.Item;
import com.haloofwar.entities.statics.Obstacle;
import com.haloofwar.interfaces.CollisionVisitor;

public class LivingEntityCollisionBehavior implements CollisionVisitor{

	@Override
	public void visit(Bullet bullet, Entity entity) {
		bullet.destroy();
	}

    @Override
    public void visit(LivingEntity living, Entity entity) {
        MovementComponent mov = living.getMovement();

        float currentX = mov.getX();
        float currentY = mov.getY();
        float lastX = mov.getLastX();
        float lastY = mov.getLastY();

        // Probar revertir solo el eje X
        mov.setX(lastX);
        mov.setY(currentY);
        living.setPosition(lastX, currentY);
        if (!living.getBounds().overlaps(entity.getBounds())) {
            return; // sólo X causaba la colisión
        }

        // Probar revertir solo el eje Y
        mov.setX(currentX);
        mov.setY(lastY);
        living.setPosition(currentX, lastY);
        if (!living.getBounds().overlaps(entity.getBounds())) {
            return; // sólo Y causaba la colisión
        }

        // Revertir ambos
        mov.setX(lastX);
        mov.setY(lastY);
        living.setPosition(lastX, lastY);
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
