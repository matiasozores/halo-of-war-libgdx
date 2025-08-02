package com.haloofwar.factories;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.entities.WeaponType;
import com.haloofwar.weapons.Weapon;
import com.haloofwar.weapons.guns.AssaultRifle;

public class WeaponFactory {
	private final GameContext context;

	public WeaponFactory(GameContext context) {
		this.context = context;
	}

	public Weapon create(WeaponType type) {
		switch (type) {
		case RIFLE_ASALTO:

			return new AssaultRifle(this.context.getInput(), this.context.getGameplay().getBullets(),
					this.context.getTexture(), this.context.getGameplay().getCollisions(),
					this.context.getAudio().getSound());
		default:
			return new AssaultRifle(this.context.getInput(), this.context.getGameplay().getBullets(),
					this.context.getTexture(), this.context.getGameplay().getCollisions(),
					this.context.getAudio().getSound());
		}
	}
}
