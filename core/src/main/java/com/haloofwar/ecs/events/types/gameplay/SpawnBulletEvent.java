package com.haloofwar.ecs.events.types.gameplay;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.gameplay.BulletComponent;

public class SpawnBulletEvent {
	public Entity bullet;

	public SpawnBulletEvent(Entity bullet) {
		if(bullet.hasComponent(BulletComponent.class)) {
			this.bullet = bullet;
		}
	}

}
