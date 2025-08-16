package com.haloofwar.ecs.components.debug;

import com.haloofwar.ecs.components.Component;
import com.badlogic.gdx.graphics.Color;

public class ShapeComponent implements Component {
    public float x, y;
    public float width, height; 
    public Color color;    

    public ShapeComponent(float x, float y, float width, float height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public ShapeComponent(float x, float y, float width, float height) {
        this(x, y, width, height, Color.RED); 
    }
}
