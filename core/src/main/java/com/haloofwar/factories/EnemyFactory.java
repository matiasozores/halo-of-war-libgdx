package com.haloofwar.factories;

import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.movement.EnemyMovementController;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.enemies.Enemy;
import com.haloofwar.enumerators.PlayerType;

public class EnemyFactory {
	private final GameContext context;
	
	public EnemyFactory(GameContext context) {
		this.context = context;
	}
	
	public Enemy create(PlayerType type) {
		MovementComponent movement = new MovementComponent(new EnemyMovementController());
		AnimationComponent animation = new AnimationComponent(type, this.context.getTexture());
		
		switch (type) {
		case KRATOS:
			return new Enemy(movement, animation);
			
		case MASTER_CHIEF:
			return new Enemy(movement, animation);

		default:
			System.out.println("Ha ocurrido un error inesperado al seleccionar un personaje. ERROR 01");
			return new Enemy(movement, animation);
		}
	}
}
