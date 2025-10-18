package com.haloofwar.game.components;

import com.haloofwar.common.enumerators.Direction;
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
        return dirX;
    }

    @Override
    public float getDirectionY() {
        return dirY;
    }

    @Override
    public void update(float delta) {
        if (!active || transform == null) return;

        transform.x += dirX * speed * delta;
        transform.y += dirY * speed * delta;
    }

    @Override
    public void changeTarget(Entity entity) {
        if (entity != null && entity.hasComponent(TransformComponent.class)) {
            this.transform = entity.getComponent(TransformComponent.class);
        }
    }

    @Override
    public void onUpdatePosition(Direction direction, boolean isPressed) {
        // las balas no reaccionan a eventos de dirección
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

	@Override
	public void setMove(boolean canMove) {
		
	}
}
