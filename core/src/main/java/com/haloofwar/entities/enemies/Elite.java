package com.haloofwar.entities.enemies;

import com.haloofwar.components.animations.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.components.EntitySoundHandler;
import com.haloofwar.entities.components.EntityStateHandler;

public class Elite extends Enemy{
	
	public Elite(
		MovementComponent movement,
		AnimationComponent animation,
		EntitySoundHandler sound,
		EntityStateHandler state
	) {

		super("Elite", movement, animation, sound, state);
	}
}
