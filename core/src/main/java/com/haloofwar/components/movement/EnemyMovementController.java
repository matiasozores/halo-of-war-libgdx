package com.haloofwar.components.movement;

import com.haloofwar.interfaces.MovementController;

public class EnemyMovementController implements MovementController {
	
	/* * Esta es una implementación provisional para el EnemyMovementController.
	* No proporciona ninguna lógica de movimiento, ya que los enemigos suelen ser controlados
	* por la IA u otra lógica del juego.
	*/
	
	@Override
	public float getDirectionX() {
		return 0f;
	}

	@Override
	public float getDirectionY() {
		return 0f;
	}
}

