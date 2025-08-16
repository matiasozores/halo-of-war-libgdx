package com.haloofwar.ecs.components.physics.movement;

import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.input.MoveEvent;
import com.haloofwar.interfaces.entities.MovementController;

public class PlayerMovementController implements MovementController{
	
    private float dx = 0;
    private float dy = 0;
	
    public PlayerMovementController(EventBus bus) {
        bus.subscribe(MoveEvent.class, this::onMoveEvent);
    }
    
    private void onMoveEvent(MoveEvent event) {
        switch (event.getDirection()) {
            case LEFT:
                this.dx = event.isPressed() ? -1 : (this.dx == -1 ? 0 : this.dx);
                break;
            case RIGHT:
            	this.dx = event.isPressed() ? 1 : (this.dx == 1 ? 0 : this.dx);
                break;
            case UP:
            	this.dy = event.isPressed() ? 1 : (this.dy == 1 ? 0 : this.dy);
                break;
            case DOWN:
            	this.dy = event.isPressed() ? -1 : (this.dy == -1 ? 0 : this.dy);
                break;
        }
    }

    @Override
    public float getDirectionX() {
        return this.dx;
    }

    @Override
    public float getDirectionY() {
        return this.dy;
    }
}
