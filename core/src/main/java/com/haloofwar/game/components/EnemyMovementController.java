package com.haloofwar.game.components;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.interfaces.MovementController;

public class EnemyMovementController implements MovementController {

    private float dx = 0, dy = 0;

    @Override
    public float getDirectionX() { return dx; }

    @Override
    public float getDirectionY() { return dy; }
    
    @Override
    public void changeTarget(Entity newTarget) {
        // lógica de IA si querés
    }

    @Override
    public void setDirection(float x, float y) {
        this.dx = x;
        this.dy = y;
    }
}
