package com.haloofwar.interfaces;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.entities.Entity;
import com.haloofwar.enumerators.entities.behavior.CollisionType;

public interface Collidable {
    Rectangle getBounds();
    CollisionType getCollisionType();
    CollisionVisitor getCollisionBehavior();
 
    void accept(CollisionVisitor visitor, Entity self);
}

