package com.haloofwar.entities.characters;

import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.collision.CollisionManager;
import com.haloofwar.dependences.BulletManager;
import com.haloofwar.dependences.InputManager;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.enumerators.CharacterType;
import com.haloofwar.weapons.guns.AssaultRifle;

public class Kratos extends Player {

	public Kratos(InputManager inputManager, BulletManager bulletManager, GameWorldCamera camera, TextureManager textureManager, CollisionManager collisionManager) {
		super("Kratos", new AssaultRifle(inputManager, bulletManager, textureManager, collisionManager), CharacterType.KRATOS, inputManager, camera, textureManager, collisionManager);
	}

}
