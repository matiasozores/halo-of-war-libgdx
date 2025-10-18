package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.interfaces.Weapon;

public class AddWeaponToInventoryEvent {
	public final PlayerType playerType;
	public final Weapon weapon;
	
	public AddWeaponToInventoryEvent(Weapon weapon, PlayerType playerType) {
		this.playerType = playerType;
		this.weapon = weapon;
	}
}
