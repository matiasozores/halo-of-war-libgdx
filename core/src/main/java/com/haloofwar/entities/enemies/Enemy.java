package com.haloofwar.entities.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.collision.CollisionManager;
import com.haloofwar.components.movement.EnemyMovementController;
import com.haloofwar.dependences.InputManager;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.entities.Entity;
import com.haloofwar.enumerators.CharacterType;

public class Enemy extends Entity{

	public Enemy(CharacterType sprite, InputManager inputManager, GameWorldCamera camera, TextureManager textureManager, CollisionManager collisionManager) {
		super("Enemigo 01", sprite, new EnemyMovementController(), camera, textureManager, collisionManager);
	}
	
	public void update(float delta) {
		super.update(delta);
	}
	
	public void render(SpriteBatch batch) {
		super.render(batch);
	}
}
