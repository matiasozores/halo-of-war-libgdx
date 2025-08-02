package com.haloofwar.dependences.collision.types;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.dependences.collision.CollisionHandler;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.entities.LivingEntity;

public class EntityWallCollisionHandler implements CollisionHandler{

	@Override
	public void handle(Collidable a, Collidable b, CollisionManager manager) {
	    Rectangle playerBounds = a.getBounds();
	    Rectangle wallBounds = b.getBounds();

	    if (playerBounds.overlaps(wallBounds)) {

	        if (!(a instanceof LivingEntity)) {
	            System.out.println("NO ES");
	        	return;
	        }
	        
	        
	        // Calculamos las distancias de solapamiento
	        float overlapLeft = playerBounds.x + playerBounds.width - wallBounds.x;
	        float overlapRight = wallBounds.x + wallBounds.width - playerBounds.x;
	        float overlapTop = wallBounds.y + wallBounds.height - playerBounds.y;
	        float overlapBottom = playerBounds.y + playerBounds.height - wallBounds.y;

	        float minOverlapX = Math.min(overlapLeft, overlapRight);
	        float minOverlapY = Math.min(overlapTop, overlapBottom);

	        MovementComponent movement = ((LivingEntity) a).getMovement();

	        if (minOverlapX < minOverlapY) {
	            if (overlapLeft < overlapRight) {
	                movement.setX(movement.getX() - overlapLeft);
	            } else {
	                movement.setX(movement.getX() + overlapRight);
	            }
	        } else {
	            if (overlapTop < overlapBottom) {
	                movement.setY(movement.getY() + overlapTop);
	            } else {
	                movement.setY(movement.getY() - overlapBottom);
	            }
	        }
	    }
	}




}
