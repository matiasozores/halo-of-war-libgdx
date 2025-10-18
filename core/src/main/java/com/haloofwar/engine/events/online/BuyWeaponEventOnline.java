package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.interfaces.Weapon;

public class BuyWeaponEventOnline {
	public final Weapon weapon;
	public final PlayerType playerType;
	
	public BuyWeaponEventOnline(Weapon weapon, PlayerType playerType) {
		this.weapon = weapon;
		this.playerType = playerType;
	}	
}
