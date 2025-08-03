package com.haloofwar.entities.enemies;

import com.haloofwar.components.animations.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.components.EntitySoundHandler;
import com.haloofwar.entities.components.EntityStateHandler;

public class Enemy extends LivingEntity {
	
	public Enemy(
		String name, 
		MovementComponent movement, 
		AnimationComponent animation, 
		EntitySoundHandler sound,
		EntityStateHandler state
	) {
		super(name, movement, animation, sound, state);
	}
}
