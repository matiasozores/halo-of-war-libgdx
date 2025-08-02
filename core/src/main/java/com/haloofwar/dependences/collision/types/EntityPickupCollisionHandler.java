package com.haloofwar.dependences.collision.types;

import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.dependences.collision.CollisionHandler;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.dependences.gameplay.ObjectManager;
import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.entities.statics.Item;

public class EntityPickupCollisionHandler implements CollisionHandler{

	private InputManager input;
	private ObjectManager objects;
	
	public EntityPickupCollisionHandler(InputManager input, ObjectManager objects) {
		this.input = input;
		this.objects = objects;
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
			player.getInventory().add(item);
			manager.removeCollidable(item);
			this.objects.removeObject(item);
		}
		
	}

}
