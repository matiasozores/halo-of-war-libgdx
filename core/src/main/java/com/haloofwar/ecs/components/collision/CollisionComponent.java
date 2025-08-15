package com.haloofwar.ecs.components.collision;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.ecs.components.Component;

public class CollisionComponent implements Component{
	public Rectangle bounds;
	public boolean active = true;
	
	public CollisionComponent(Rectangle bounds) {
		this.bounds = bounds;
	}
	
    public void setPosition(float x, float y) {
        this.bounds.setPosition(x, y);
    }
}
