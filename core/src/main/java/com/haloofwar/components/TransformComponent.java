package com.haloofwar.components;

import com.haloofwar.interfaces.Component;

public class TransformComponent implements Component{
	private static float defaultOffset = 10f;
	
    public float x, y;
    public float width, height;
    public float hitboxWidth, hitboxHeight;

    public TransformComponent(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        this.hitboxWidth = this.width + defaultOffset;
        this.hitboxHeight = this.height + defaultOffset;
    }
    
    public TransformComponent(float x, float y, float hitboxWidth, float hitboxHeight, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
    }
    
    public void setPosition(float x, float y) {
    	this.x = x;
    	this.y = y;
    }
}
