package com.haloofwar.game.components;

import com.haloofwar.common.enumerators.Direction;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.interfaces.MovementController;

public class EnemyMovementController implements MovementController {

    private float dx = 0, dy = 0;
    private TransformComponent enemyTransform;
    private TransformComponent playerTransform;
    private VisibilityComponent visibilityComponent;
    private final float minDistance = 100f; // distancia mínima al jugador

    public EnemyMovementController(TransformComponent enemyTransform, TransformComponent playerTransform, VisibilityComponent visibilityComponent) {
        this.enemyTransform = enemyTransform;
        this.playerTransform = playerTransform;
        this.visibilityComponent = visibilityComponent;
    }

    @Override
    public float getDirectionX() { return dx; }

    @Override
    public float getDirectionY() { return dy; }

    @Override
    public void update(float delta) {
        if(this.visibilityComponent.isVisible()) {
            float vx = playerTransform.x - enemyTransform.x;
            float vy = playerTransform.y - enemyTransform.y;

            float distance = (float)Math.sqrt(vx*vx + vy*vy);

            if (distance > minDistance) {
                dx = vx / distance;
                dy = vy / distance;
            } else {
                dx = 0;
                dy = 0;
            }
        }
    }
    
    @Override
    public void changeTarget(Entity newTarget) {
        if (newTarget != null && newTarget.hasComponent(TransformComponent.class)) {
            this.playerTransform = newTarget.getComponent(TransformComponent.class);
        } else {
            System.out.println("El nuevo target no tiene TransformComponent o es nulo.");
        }
    }

	@Override
	public void onUpdatePosition(Direction direction, boolean isPressed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMove(boolean canMove) {
		// TODO Auto-generated method stub
		
	}
}


