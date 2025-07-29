package com.haloofwar.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.collision.CollisionManager;
import com.haloofwar.components.movement.PlayerMovementController;
import com.haloofwar.dependences.InputManager;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.entities.Entity;
import com.haloofwar.enumerators.CharacterType;
import com.haloofwar.ui.Crosshair;
import com.haloofwar.weapons.Weapon;

public abstract class Player extends Entity{
	private Crosshair crosshair;
	private Weapon weapon;

	public Player(String name, Weapon weapon, CharacterType sprite, InputManager input, GameWorldCamera camera, TextureManager textureManager, CollisionManager collisionManager) {
		super(name, sprite, new PlayerMovementController(input), camera, textureManager, collisionManager);
		this.crosshair = new Crosshair(sprite.getCrosshairPath(), camera);
		this.weapon = weapon;
	}
	
	public void update(float delta) {
		super.update(delta);
		this.crosshair.update();
		this.weapon.update(delta, this.getX(), this.getY(), this.getMouseX(), this.getMouseY());
	}
	
	public void render(SpriteBatch batch) {
		super.render(batch);
		this.crosshair.render(batch);
	}
	
	public float getMouseX() {
		return this.crosshair.getMouseX();
	}
	
	public float getMouseY() {
		return this.crosshair.getMouseY();
	}
	
	// Colisiones
	

}
