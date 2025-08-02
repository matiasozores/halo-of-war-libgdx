package com.haloofwar.entities.enemies;

import com.haloofwar.components.animations.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.components.EntitySoundHandler;
import com.haloofwar.enumerators.entities.behavior.CollisionType;

public class Enemy extends LivingEntity {
	
	public Enemy(
		String name, 
		MovementComponent movement, 
		AnimationComponent animation, 
		EntitySoundHandler sound
	) {
		super(name, movement, animation, CollisionType.ENTITY, sound);
	}
}
