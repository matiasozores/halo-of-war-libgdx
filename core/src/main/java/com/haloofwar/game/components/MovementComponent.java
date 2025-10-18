package com.haloofwar.game.components;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.interfaces.MovementController;

public class MovementComponent implements Component {
    public MovementController controller; 
    public float lastX, lastY, speed;         

    public MovementComponent(MovementController controller, float speed) {
        this.controller = controller;
        this.lastX = 0;
        this.lastY = 0;
        this.speed = speed;
    }
    
    public void changeTarget(Entity entity) {
    	this.controller.changeTarget(entity);
    }
}