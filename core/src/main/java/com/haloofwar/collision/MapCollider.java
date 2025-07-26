package com.haloofwar.collision;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.enumerators.CollisionType;

public class MapCollider implements Collidable {
    private final Rectangle bounds;

    public MapCollider(float x, float y, float width, float height) {
        this.bounds = new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.WALL;
    }
}

