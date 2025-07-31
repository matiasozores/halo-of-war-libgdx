package com.haloofwar.entities.enemies;

import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.Entity;

public class Enemy extends Entity {
	public Enemy(MovementComponent movement, AnimationComponent animation) {
		super("Enemigo 01", movement, animation);
	}
}
