package com.haloofwar.game.components;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.online.EntityMoveEventOnline;
import com.haloofwar.engine.events.online.MoveEventOnline;
import com.haloofwar.engine.interfaces.MovementController;

public class PlayerRemoteMovementController implements MovementController{
	
    private float dx = 0;
    private float dy = 0;
	private final int IDENTIFIER;
    private final EventBus bus;
	
    public PlayerRemoteMovementController(final int IDENTIFIER, EventBus bus) {
    	this.IDENTIFIER = IDENTIFIER;
    	this.bus = bus;
    	this.bus.subscribe(EntityMoveEventOnline.class, this::onMoveEvent);
    }
    
    private void onMoveEvent(EntityMoveEventOnline event) {    	
    	this.bus.publish(new MoveEventOnline(this.IDENTIFIER, event.getDirection(), event.isPressed()));
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
		
	}

	@Override
	public void setDirection(float x, float y) {
		this.dx = x;
		this.dy = y;
	}
}
