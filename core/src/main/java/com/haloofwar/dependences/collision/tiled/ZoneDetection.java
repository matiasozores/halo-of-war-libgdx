package com.haloofwar.dependences.collision.tiled;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.enumerators.entities.behavior.CollisionType;

public class ZoneDetection implements Collidable {
	private final Rectangle bounds;

	public ZoneDetection(float x, float y, float width, float height) {
		this.bounds = new Rectangle(x, y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return this.bounds;
	}

	@Override
	public CollisionType getCollisionType() {
		return CollisionType.ZONE_DETECTION;
	}
	
}
