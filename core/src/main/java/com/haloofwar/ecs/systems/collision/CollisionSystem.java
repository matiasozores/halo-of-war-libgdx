package com.haloofwar.ecs.systems.collision;

import java.util.ArrayList;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.collision.CollisionComponent;
import com.haloofwar.ecs.components.physics.TransformComponent;
import com.haloofwar.ecs.events.CollisionEvent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.systems.ECSSystem;

public class CollisionSystem implements ECSSystem {

	private final EventBus bus;
	private final ArrayList<Entity> collidables = new ArrayList<>();

	public CollisionSystem(EventBus bus) {
		this.bus = bus;
	}

	@Override
	public void register(Entity e) {
		if (e.hasComponent(CollisionComponent.class) && e.hasComponent(TransformComponent.class)) {
			if (!this.collidables.contains(e)) {
				this.collidables.add(e);
			}
		}
	}

	@Override
	public void unregister(Entity e) {
		this.collidables.remove(e);
	}

	@Override
	public void update(float delta) {
		ArrayList<Entity> snapshot = new ArrayList<>(this.collidables);
		int size = snapshot.size();

		for (int i = 0; i < size - 1; i++) {
			Entity entityA = snapshot.get(i);
			CollisionComponent collisionA = entityA.getComponent(CollisionComponent.class);
			TransformComponent transformA = entityA.getComponent(TransformComponent.class);

			if (!collisionA.active || transformA == null) {
				continue;
			}
			collisionA.setPosition(transformA.x, transformA.y);

			for (int j = i + 1; j < size; j++) {
				Entity entityB = snapshot.get(j);
				CollisionComponent collisionB = entityB.getComponent(CollisionComponent.class);
				TransformComponent transformB = entityB.getComponent(TransformComponent.class);

				if (!collisionB.active || transformB == null) {
					continue;
				}
				collisionB.setPosition(transformB.x, transformB.y);

				if (collisionA.bounds.overlaps(collisionB.bounds)) {
					this.bus.publish(new CollisionEvent(entityA, entityB));
				}
			}
		}
	}
	
	@Override
	public void dispose() {
		this.collidables.clear();
	}

}
