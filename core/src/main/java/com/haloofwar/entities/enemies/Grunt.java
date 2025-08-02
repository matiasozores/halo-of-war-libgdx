package com.haloofwar.entities.enemies;

import com.haloofwar.components.animations.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;

public class Grunt extends Enemy{
	
	public Grunt(MovementComponent movement, AnimationComponent animation) {
		super("Grunt", movement, animation);
	}
}
