package com.haloofwar.entities.statics.items;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.dependences.collision.behaviors.ItemCollisionBehavior;
import com.haloofwar.entities.components.EntityStateHandler;

public class Potion extends Item{

	public Potion(Texture texture, float x, float y, int width, int height, EntityStateHandler state,
			ItemCollisionBehavior collisionBehavior) {
		super("Posion sin efectos", texture, x, y, width, height, state, collisionBehavior);
	}

}
