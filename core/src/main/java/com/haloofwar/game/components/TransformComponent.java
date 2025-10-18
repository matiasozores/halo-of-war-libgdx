package com.haloofwar.game.components;

public class TransformComponent implements Component{
	private static final float DEFAULT_OFFSET = 10f;
	
    public float x, y;
    public float width, height;
    public float hitboxWidth, hitboxHeight;
    public final int identifier;
    
    public TransformComponent(final int identifier, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        this.hitboxWidth = this.width + DEFAULT_OFFSET;
        this.hitboxHeight = this.height + DEFAULT_OFFSET;
        
        this.identifier = identifier;
    }
    
    public TransformComponent(final int identifier, float x, float y, float hitboxWidth, float hitboxHeight, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
        
        this.identifier = identifier;
    } 
}
