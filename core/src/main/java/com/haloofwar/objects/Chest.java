package com.haloofwar.objects;

import com.haloofwar.collision.CollisionManager;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.enumerators.ObjectType;

public class Chest extends ObjectBase {

	public Chest(TextureManager textureManager, CollisionManager collisionManager) {
		super("Cofre del tesoro", ObjectType.CHEST, textureManager, collisionManager);
	}
}
