package com.haloofwar.components;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.interfaces.Component;

public class CollisionComponent implements Component {
    public final Rectangle bounds;
    public boolean active = true;
    
    public CollisionComponent(float width, float height) {
        this.bounds = new Rectangle(0, 0, width, height);
    }
    
    public CollisionComponent(float width, float height, float x, float y) {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void syncWithTransform(TransformComponent transform) {
        this.bounds.setPosition(transform.x, transform.y);
        this.bounds.setSize(transform.hitboxWidth, transform.hitboxHeight);
    }
}

