package com.haloofwar.components;

import com.haloofwar.interfaces.Component;
import com.haloofwar.interfaces.MovementController;

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