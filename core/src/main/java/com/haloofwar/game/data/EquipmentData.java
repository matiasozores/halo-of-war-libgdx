package com.haloofwar.game.data;

import java.io.Serializable;
import java.util.ArrayList;

public class EquipmentData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String currentWeaponName;
	public ArrayList<String> weaponInventoryNames = new ArrayList<String>();
}
