package com.haloofwar.dependences.collision.behaviors;

import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.entities.Bullet;
import com.haloofwar.entities.Entity;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.players.Player;
import com.haloofwar.entities.statics.Obstacle;
import com.haloofwar.entities.statics.items.Item;
import com.haloofwar.enumerators.entities.behavior.CollisionType;
import com.haloofwar.interfaces.Collidable;
import com.haloofwar.interfaces.CollisionVisitor;

public class ItemCollisionBehavior implements CollisionVisitor {

	private final InputManager input;
	
	public ItemCollisionBehavior(InputManager input) {
		this.input = input;
	}
	
	@Override
	public void visit(Bullet bullet, Collidable entity) {}
	
	@Override
	public void visit(LivingEntity living, Collidable entity) {
		if(entity instanceof Item item && living instanceof Player player) {
			if(this.input.isInteract()) {
				player.addItemToInventory(item);
				item.kill();
			}
		}
	}

	@Override
	public void visit(Obstacle obstacle, Collidable entity) {
		
	}

	@Override
	public void visit(Item item, Collidable entity) {
		
	}
	
	@Override
	public void visit(Entity entity, Collidable otherEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CollisionType getCollisionType() {
		return CollisionType.ITEM;
	}

	

}
