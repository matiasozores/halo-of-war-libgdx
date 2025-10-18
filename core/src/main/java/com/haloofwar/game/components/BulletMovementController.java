package com.haloofwar.game.components;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.interfaces.MovementController;

public class BulletMovementController implements MovementController {

    private float dirX;
    private float dirY;
    private float speed;
    private boolean active = true;
    private TransformComponent transform;

    public BulletMovementController(TransformComponent transform, float dirX, float dirY, float speed) {
        this.transform = transform;
        this.dirX = dirX;
        this.dirY = dirY;
        this.speed = speed;
    }

	@Override
	public float getDirectionX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDirectionY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDirection(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeTarget(Entity entity) {
		// TODO Auto-generated method stub
		
	}

}
