package com.haloofwar.entities.players;

import com.haloofwar.components.animations.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.components.EntitySoundHandler;
import com.haloofwar.entities.components.EntityStateHandler;
import com.haloofwar.enumerators.entities.PlayerType;
import com.haloofwar.ui.Crosshair;
import com.haloofwar.weapons.Weapon;

public class Kratos extends Player {

	public Kratos(
		MovementComponent movement,
		AnimationComponent animation,
		Crosshair crosshair,
		Weapon weapon,
		EntitySoundHandler sound,
		EntityStateHandler state
	) {
		super("Kratos", movement, animation, crosshair, weapon, PlayerType.KRATOS, sound, state);
	}

}
