package com.haloofwar.entities.statics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.dependences.collision.behaviors.ObstacleCollisionBehavior;
import com.haloofwar.entities.StaticEntity;
import com.haloofwar.entities.components.EntityStateHandler;

public class Obstacle extends StaticEntity {
	
    public Obstacle(float x, float y, int width, int height, EntityStateHandler state) {
        super("Pared", x, y, width, height, state);
		this.collisionBehavior = new ObstacleCollisionBehavior();
    }

	@Override
	public void render(SpriteBatch batch) {
		// El mapa ya renderiza los obstáculos, no es necesario renderizar aquí
	}

}
