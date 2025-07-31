package com.haloofwar.objects;

import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.enumerators.ObjectType;

public class Chest extends ObjectBase {

	public Chest(TextureManager textureManager, CollisionManager collisionManager) {
		super("Cofre del tesoro", ObjectType.CHEST, textureManager, collisionManager);
	}
}
