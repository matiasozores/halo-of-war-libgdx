package com.haloofwar.factories;

import com.haloofwar.components.animations.AnimationComponent;
import com.haloofwar.components.movement.EnemyMovementController;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.components.EntitySoundHandler;
import com.haloofwar.entities.enemies.Enemy;
import com.haloofwar.entities.enemies.Elite;
import com.haloofwar.enumerators.entities.EnemyType;

public class EnemyFactory {
	private final GameContext context;
	
	public EnemyFactory(GameContext context) {
		this.context = context;
	}
	
	public Enemy create(EnemyType type) {
		
		// Lo mismo que en player, valores provisionales de coordenadas, hay que reemplazarlos por los del mapa
		MovementComponent movement = new MovementComponent(new EnemyMovementController(), 750, 325);
		AnimationComponent animation = new AnimationComponent(type, this.context.getTexture());
		EntitySoundHandler sound = new EntitySoundHandler(this.context.getAudio().getSound());
		
		
		switch (type) {
		case ELITE:
			return new Elite(movement, animation, sound);
			
		default:
			return new Elite(movement, animation, sound);
		}
	}
}
