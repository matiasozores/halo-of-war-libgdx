package com.haloofwar.enumerators;

import com.haloofwar.interfaces.EntityDescriptor;

public enum PlayerType implements EntityDescriptor {
	
	MASTER_CHIEF("sprites/masterchief.png", 6, 8, CrosshairType.GREEN, WeaponType.RIFLE_ASALTO, HeadType.MASTER_CHIEF),
	KRATOS("sprites/kratos.png", 4, 4, CrosshairType.RED, WeaponType.RIFLE_ASALTO, HeadType.KRATOS);
	
	private int idleLength, walkLength;
	private String path;
	private CrosshairType crosshair;
	private WeaponType defaultWeapon;
	private HeadType head;
	
	private PlayerType(String folder, int idleLength, int walkLength, CrosshairType crosshair, WeaponType defaultWeapon, HeadType head) {
		this.path = folder;
		this.idleLength = idleLength;
		this.walkLength = walkLength;
		this.defaultWeapon = defaultWeapon;
		this.crosshair = crosshair;
		this.head = head;
	}	
	
	@Override
	public String getPath() {
		return this.path;
	}
	
	@Override
	public int getIdleLength() {
		return this.idleLength;
	}
	
	@Override
	public int getWalkLength() {
		return this.walkLength;
	}
	
	public WeaponType getDefaultWeapon() {
		return this.defaultWeapon;
	}
	
	public CrosshairType getCrosshair() {
		return this.crosshair;
	}
	
	public HeadType getHead() {
		return this.head;
	}
}
