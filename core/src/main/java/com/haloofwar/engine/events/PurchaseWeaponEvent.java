package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.interfaces.Weapon;

public class PurchaseWeaponEvent {
	public final PlayerType playerType;
	public final Weapon weapon;
		
	public PurchaseWeaponEvent(PlayerType playerType, Weapon weapon) {
		this.playerType = playerType;
		this.weapon = weapon;
	}
}
