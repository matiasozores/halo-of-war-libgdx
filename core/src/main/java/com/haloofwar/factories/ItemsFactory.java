package com.haloofwar.factories;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.collision.behaviors.ItemCollisionBehavior;
import com.haloofwar.entities.components.EntityStateHandler;
import com.haloofwar.entities.statics.Item;
import com.haloofwar.enumerators.entities.ObjectType;

public class ItemsFactory {
	private final GameContext context;
	
	public ItemsFactory(GameContext context) {
		this.context = context;
	}
	
	public Item create(float x, float y, int width, int height) {
		Texture texture = this.context.getTexture().get(ObjectType.POTION);
		EntityStateHandler state = new EntityStateHandler(this.context.getGameplay().getCollisions(), this.context.getGameplay().getEntities());
		ItemCollisionBehavior collisionBehavior = new ItemCollisionBehavior(this.context.getInput());
		return new Item(texture, x, y, width, height, state, collisionBehavior);
	}
}
