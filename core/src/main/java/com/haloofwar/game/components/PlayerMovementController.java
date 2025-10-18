package com.haloofwar.game.components;

import com.haloofwar.common.enumerators.Direction;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.MoveEvent;
import com.haloofwar.engine.interfaces.MovementController;

public class PlayerMovementController implements MovementController{
	
    private float dx = 0;
    private float dy = 0;
	private boolean canMove = true;
    
    
    public PlayerMovementController(EventBus bus) {
        bus.subscribe(MoveEvent.class, this::onMoveEvent);
    }
    
    private void onMoveEvent(MoveEvent event) {
    	if(!this.canMove) {
    		return;
    	}
    	
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

	@Override
	public void changeTarget(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdatePosition(Direction direction, boolean isPressed) {
		if(!this.canMove) {
    		return;
    	}
		
        switch (direction) {
	        case LEFT:
	            this.dx = isPressed ? -1 : (this.dx == -1 ? 0 : this.dx);
	            break;
	        case RIGHT:
	        	this.dx = isPressed ? 1 : (this.dx == 1 ? 0 : this.dx);
	            break;
	        case UP:
	        	this.dy = isPressed ? 1 : (this.dy == 1 ? 0 : this.dy);
	            break;
	        case DOWN:
	        	this.dy = isPressed ? -1 : (this.dy == -1 ? 0 : this.dy);
	            break;
        }
	}

	@Override
	public void setMove(boolean canMove) {
		this.canMove = canMove;
		if(!canMove) {
			this.dx = 0;
			this.dy = 0;
		}
	}
}
