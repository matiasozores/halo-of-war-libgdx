package com.haloofwar.events;

import com.haloofwar.components.BulletComponent;
import com.haloofwar.components.Entity;

public class SpawnBulletEvent {
	public Entity bullet;

	public SpawnBulletEvent(Entity bullet) {
		if(bullet.hasComponent(BulletComponent.class)) {
			this.bullet = bullet;
		}
	}

}
