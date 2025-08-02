package com.haloofwar.entities.enemies;

import com.haloofwar.components.animations.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.components.EntitySoundHandler;

public class Elite extends Enemy{
	
	public Elite(
		MovementComponent movement,
		AnimationComponent animation,
		EntitySoundHandler sound
	) {
		super("Elite", movement, animation, sound);
	}
}
