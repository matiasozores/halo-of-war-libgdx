package com.haloofwar.enumerators;

import com.haloofwar.interfaces.CrosshairEntityDescriptor;

public enum PlayerType implements CrosshairEntityDescriptor {
	MASTER_CHIEF("Master Chief","sprites/masterchief.png", 6, 8, CrosshairType.GREEN, WeaponType.RIFLE_ASALTO, HeadType.MASTER_CHIEF),
	KRATOS("Kratos","sprites/kratos.png", 4, 4, CrosshairType.RED, WeaponType.RIFLE_ASALTO, HeadType.KRATOS);

	private final String name;
	private final String path;
	private final int idleLength;
	private final int walkLength;
	private final CrosshairType crosshair;
	private final WeaponType defaultWeapon;
	private final HeadType head;

	PlayerType(String name, String path, int idleLength, int walkLength, CrosshairType crosshair, WeaponType defaultWeapon,
			HeadType head) {
		this.name = name;
		this.path = path;
		this.idleLength = idleLength;
		this.walkLength = walkLength;
		this.crosshair = crosshair;
		this.defaultWeapon = defaultWeapon;
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

	@Override
	public WeaponType getDefaultWeapon() {
		return this.defaultWeapon;
	}

	@Override
	public CrosshairType getCrosshair() {
		return this.crosshair;
	}

	@Override
	public HeadType getHead() {
		return this.head;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}
