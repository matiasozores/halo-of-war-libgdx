package com.haloofwar.ecs.components.physics;

import com.haloofwar.ecs.components.Component;

public class TransformComponent implements Component{
    public float x, y;
    public float width, height;
    public float hitboxWidth, hitboxHeight;

    public TransformComponent(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        this.hitboxWidth = this.width;
        this.hitboxHeight = this.height;
    }
    
    public TransformComponent(float x, float y, float hitboxWidth, float hitboxHeight, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
    }
}
