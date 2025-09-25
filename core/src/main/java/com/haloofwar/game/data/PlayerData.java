package com.haloofwar.game.data;

import java.io.Serializable;

import com.haloofwar.common.enums.PlayerType;

public class PlayerData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public InventoryData inventory;
	public EquipmentData equipment;
	public PlayerType type;
	
}
