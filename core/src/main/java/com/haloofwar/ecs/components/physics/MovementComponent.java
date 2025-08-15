package com.haloofwar.ecs.components.physics;

import com.haloofwar.ecs.components.Component;
import com.haloofwar.interfaces.entities.MovementController;

public class MovementComponent implements Component {
    public MovementController controller; 
    public float lastX, lastY, speed;         

    public MovementComponent(MovementController controller, float speed) {
        this.controller = controller;
        this.lastX = 0;
        this.lastY = 0;
        this.speed = speed;
    }
}