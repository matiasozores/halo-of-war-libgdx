package com.haloofwar.factories;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.collision.behaviors.ItemCollisionBehavior;
import com.haloofwar.entities.components.EntityStateHandler;
import com.haloofwar.entities.statics.items.Item;
import com.haloofwar.entities.statics.items.Potion;
import com.haloofwar.enumerators.entities.objects.ItemType;

public class ItemsFactory {
	private final GameContext context;
	
	public ItemsFactory(GameContext context) {
		this.context = context;
	}
	
	public Item create(float x, float y, int width, int height, ItemType type) {
		Texture texture = this.context.getTexture().get(type);
		EntityStateHandler state = new EntityStateHandler(this.context.getGameplay().getCollisions(), this.context.getGameplay().getEntities());
		ItemCollisionBehavior collisionBehavior = new ItemCollisionBehavior(this.context.getInput());
		
		switch (type) {
			case POSION_SIN_EFECTOS:
				return new Potion(texture, x, y, width, height, state, collisionBehavior);
			default:
				return new Potion(texture, x, y, width, height, state, collisionBehavior);
		}
		
	}
}
