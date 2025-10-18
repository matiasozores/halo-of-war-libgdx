package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.interfaces.Weapon;

public class UpdateCurrentWeaponEvent {
	public final Weapon weapon;
	public final PlayerType playerType;
	
	public UpdateCurrentWeaponEvent(PlayerType playerType, Weapon weapon) {
		this.weapon = weapon;
		this.playerType = playerType;
	}
}
