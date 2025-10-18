package com.haloofwar.game.components;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.interfaces.MovementController;

public class PlayerDefaultRemoteMovementController implements MovementController{
	
    private float dx = 0;
    private float dy = 0;

	
    public PlayerDefaultRemoteMovementController() {
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
