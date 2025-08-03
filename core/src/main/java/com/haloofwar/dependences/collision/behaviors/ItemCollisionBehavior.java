package com.haloofwar.dependences.collision.behaviors;

import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.entities.Bullet;
import com.haloofwar.entities.Entity;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.players.Player;
import com.haloofwar.entities.statics.Item;
import com.haloofwar.entities.statics.Obstacle;
import com.haloofwar.interfaces.CollisionVisitor;

public class ItemCollisionBehavior implements CollisionVisitor {

	private final InputManager input;
	
	public ItemCollisionBehavior(InputManager input) {
		this.input = input;
	}
	
	@Override
	public void visit(Bullet bullet, Entity entity) {}
	
	@Override
	public void visit(LivingEntity living, Entity entity) {
		if(entity instanceof Item item && living instanceof Player player) {
			if(this.input.isInteract()) {
				player.addItemToInventory(item);
				item.kill();
			}
		}
	}

	@Override
	public void visit(Obstacle obstacle, Entity entity) {
		
	}

	@Override
	public void visit(Item item, Entity entity) {
		
	}
	
	@Override
	public void visit(Entity entity, Entity otherEntity) {
		// TODO Auto-generated method stub
		
	}

	

}
