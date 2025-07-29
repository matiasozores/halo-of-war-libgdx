package com.haloofwar.collision.types;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.collision.Collidable;
import com.haloofwar.collision.CollisionHandler;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.Entity;

public class EntityWallCollisionHandler implements CollisionHandler{

	@Override
	public void handle(Collidable a, Collidable b) {
	    Rectangle playerBounds = a.getBounds();
	    Rectangle wallBounds = b.getBounds();

	    if (playerBounds.overlaps(wallBounds)) {
	        // Calculamos las distancias de solapamiento
	        float overlapLeft = playerBounds.x + playerBounds.width - wallBounds.x;
	        float overlapRight = wallBounds.x + wallBounds.width - playerBounds.x;
	        float overlapTop = wallBounds.y + wallBounds.height - playerBounds.y;
	        float overlapBottom = playerBounds.y + playerBounds.height - wallBounds.y;

	        float minOverlapX = Math.min(overlapLeft, overlapRight);
	        float minOverlapY = Math.min(overlapTop, overlapBottom);

	        MovementComponent movement = ((Entity) a).getMovement();

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
