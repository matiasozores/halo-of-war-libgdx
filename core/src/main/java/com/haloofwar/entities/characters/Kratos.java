package com.haloofwar.entities.characters;

import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.enumerators.PlayerType;
import com.haloofwar.ui.Crosshair;
import com.haloofwar.weapons.Weapon;

public class Kratos extends Player {

	public Kratos(MovementComponent movement, AnimationComponent animation, Crosshair crosshair, Weapon weapon) {
		super("Kratos", movement, animation, crosshair, weapon, PlayerType.KRATOS);
	}

}
