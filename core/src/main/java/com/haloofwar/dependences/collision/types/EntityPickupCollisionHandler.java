package com.haloofwar.dependences.collision.types;

import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.dependences.collision.CollisionHandler;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.dependences.gameplay.EntityManager;
import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.entities.statics.Item;

public class EntityPickupCollisionHandler implements CollisionHandler{

	private InputManager input;
	private EntityManager entities;
	
	public EntityPickupCollisionHandler(InputManager input, EntityManager entities) {
		this.input = input;
		this.entities = entities;
	}
	
	@Override
	public void handle(Collidable a, Collidable b, CollisionManager manager) {
		Player player;
		Item item;
		if(a instanceof Player) {
			player = (Player) a;
			item = (Item) b; 
		} else {
			player = (Player) b;
			item = (Item) a;
		}
	
		if(this.input.isInteract()) {
			player.addItemToInventory(item);
			manager.remove(item);
			this.entities.remove(item);
		}
		
	}

}
