package com.haloofwar.entities.characters;

import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.collision.CollisionManager;
import com.haloofwar.dependences.BulletManager;
import com.haloofwar.dependences.InputManager;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.enumerators.CharacterType;
import com.haloofwar.weapons.guns.AssaultRifle;

public class MasterChief extends Player {

	public MasterChief(InputManager inputManager, BulletManager bulletManager, GameWorldCamera camera, TextureManager textureManager, CollisionManager collisionManager) {
		super("Master Chief", new AssaultRifle(inputManager, bulletManager, textureManager, collisionManager), CharacterType.MASTER_CHIEF, inputManager, camera, textureManager, collisionManager); 
	}

}
