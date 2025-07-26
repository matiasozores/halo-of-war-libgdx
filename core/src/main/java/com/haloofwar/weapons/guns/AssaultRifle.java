package com.haloofwar.weapons.guns;

import com.haloofwar.collision.CollisionManager;
import com.haloofwar.dependences.BulletManager;
import com.haloofwar.dependences.InputManager;
import com.haloofwar.dependences.TextureManager;

public class AssaultRifle extends Gun{
	public AssaultRifle(InputManager inputManager, BulletManager bulletManager, TextureManager textureManager, CollisionManager collisionManager) {
		super("Rifle de Asalto", 10, 50, 20, inputManager, bulletManager, textureManager, collisionManager);
	}
}
