package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.interfaces.Weapon;

public class ChangeCurrentWeaponEvent {
	public final PlayerType playerType;
	public final Weapon weapon;
	
	public ChangeCurrentWeaponEvent(PlayerType playerType, Weapon weapon) {
		this.playerType = playerType;
		this.weapon = weapon;
	}
}
