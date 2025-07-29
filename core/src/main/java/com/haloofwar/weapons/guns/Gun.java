package com.haloofwar.weapons.guns;

import com.haloofwar.collision.CollisionManager;
import com.haloofwar.dependences.BulletManager;
import com.haloofwar.dependences.InputManager;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.weapons.Weapon;

public abstract class Gun extends Weapon {
	private final BulletManager bulletManager;
	private final TextureManager textureManager;
	private final CollisionManager collisionManager;
	
	public Gun(String name, int damage, int speed, int cooldown, InputManager inputManager, BulletManager bulletManager,
			TextureManager textureManager, CollisionManager collisionManager) {
		super(name, damage, speed, cooldown, inputManager);
		this.bulletManager = bulletManager;
		this.textureManager = textureManager;
		this.collisionManager = collisionManager;
	}

	@Override
	protected void use(float playerX, float playerY, float mouseX, float mouseY) {
		if (!this.isReady) {
			return;
		}		

		// Calculo sacado con CHATGPT
		float dx = mouseX - playerX;
		float dy = mouseY - playerY;

		float length = (float) Math.sqrt(dx * dx + dy * dy);
		if (length == 0) {
			return;
		}

		float dirX = dx / length;
		float dirY = dy / length;
		// ----------------------------
		
		float offset = 45f; // Esto representa una distancia razonable fuera del jugador

		float bulletX = playerX + dirX * offset;
		float bulletY = playerY + dirY * offset;


		Bullet bullet = new Bullet(bulletX, bulletY, dirX, dirY, this.damage, this.speed, this.textureManager, this.collisionManager);

		this.bulletManager.add(bullet);
		this.resetCooldown();
	}

	@Override
	public void update(float delta, float playerX, float playerY, float mouseX, float mouseY) {
		super.update(delta, playerX, playerY, mouseX, mouseY);
	}
}
