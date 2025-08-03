package com.haloofwar.components.movement;

import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.interfaces.MovementController;

public class PlayerMovementController implements MovementController{
    private final InputManager input;

    public PlayerMovementController(InputManager input) {
        this.input = input;
    }

    @Override
    public float getDirectionX() {
        float dx = 0;
        if (this.input.isMoveLeft()) {
        	dx -= 1;
        }
        if (this.input.isMoveRight()) {
        	dx += 1;
        }
        return dx;
    }

    @Override
    public float getDirectionY() {
        float dy = 0;
        
        if (this.input.isMoveUp()) {
        	dy += 1;
        }
        
        if (this.input.isMoveDown()) {
        	dy -= 1;
        }
        return dy;
    }
}
