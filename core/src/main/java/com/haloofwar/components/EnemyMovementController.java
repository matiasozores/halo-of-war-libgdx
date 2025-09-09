package com.haloofwar.components;

import com.haloofwar.interfaces.MovementController;

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
}


