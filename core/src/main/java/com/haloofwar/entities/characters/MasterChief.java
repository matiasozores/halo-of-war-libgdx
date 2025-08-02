package com.haloofwar.entities.characters;

import com.haloofwar.components.animations.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.components.EntitySoundHandler;
import com.haloofwar.enumerators.entities.PlayerType;
import com.haloofwar.ui.Crosshair;
import com.haloofwar.weapons.Weapon;

public class MasterChief extends Player {

	public MasterChief(
			MovementComponent movement,
			AnimationComponent animation,
			Crosshair crosshair,
			Weapon weapon,
			EntitySoundHandler sound
		) {
			super("Master Chief", movement, animation, crosshair, weapon, PlayerType.MASTER_CHIEF, sound);
		}

}
