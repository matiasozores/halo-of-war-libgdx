package com.haloofwar.dependences.collision;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.enumerators.entities.behavior.CollisionType;

public interface Collidable {
    Rectangle getBounds();
    CollisionType getCollisionType();
}

