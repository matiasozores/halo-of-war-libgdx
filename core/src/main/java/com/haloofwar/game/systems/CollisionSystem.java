package com.haloofwar.game.systems;

import java.util.ArrayList;

import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.systems.BaseSystem;
import com.haloofwar.game.components.CollisionComponent;
import com.haloofwar.game.components.MeleeAttackComponent;
import com.haloofwar.interfaces.Updatable;

public class CollisionSystem extends BaseSystem implements Updatable {

	private final EventBus bus;

	public CollisionSystem(EventBus bus) {
		this.bus = bus;
	}

	@Override
	public void register(Entity e) {
		if (e.hasComponent(CollisionComponent.class) && e.hasComponent(TransformComponent.class)) {
			super.register(e);
		}
	}
	
	@Override
	public void update(float delta) {
	    ArrayList<Entity> snapshot = new ArrayList<>(this.ENTITIES);
	    int size = snapshot.size();

	    for (int i = 0; i < size - 1; i++) {
	        Entity entityA = snapshot.get(i);
	        CollisionComponent collisionA = entityA.getComponent(CollisionComponent.class);
	        TransformComponent transformA = entityA.getComponent(TransformComponent.class);

	        if (!(!collisionA.active || transformA == null)) {
	            collisionA.syncWithTransform(transformA);

	            for (int j = i + 1; j < size; j++) {
	                Entity entityB = snapshot.get(j);
	                CollisionComponent collisionB = entityB.getComponent(CollisionComponent.class);
	                TransformComponent transformB = entityB.getComponent(TransformComponent.class);

	                if (!(!collisionB.active || transformB == null)) {
	                    collisionB.syncWithTransform(transformB);

	                    if (collisionA.bounds.overlaps(collisionB.bounds)) {
	                        this.bus.publish(new CollisionEvent(entityA, entityB));
	                    }
	                }
	            }
	        }
	    }

	    ArrayList<Entity> toRemove = new ArrayList<>();
	    for (Entity entity : snapshot) {
	        if (entity.hasComponent(MeleeAttackComponent.class)) {
	            toRemove.add(entity);
	        }
	    }
	    for (Entity entity : toRemove) {
	        this.bus.publish(new RemoveEntityEvent(entity));
	    }
	}

}
