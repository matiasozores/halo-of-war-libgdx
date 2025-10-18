package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.interfaces.Weapon;

public class ChangeWeaponEventOnline {
	public final PlayerType playerType;
	public final Weapon weapon;
	
	public ChangeWeaponEventOnline(PlayerType playerType, Weapon weapon) {
		this.playerType = playerType;
		this.weapon = weapon;
	}	
}
