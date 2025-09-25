package com.haloofwar.common.enums;

import com.haloofwar.engine.entity.CrosshairEntityDescriptor;
import com.haloofwar.interfaces.Weapon;

public enum PlayerType implements CrosshairEntityDescriptor {
	MASTER_CHIEF("Master Chief","sprites/masterchief.png", 6, 8, CrosshairType.GREEN, FireArmType.FRANCO, HeadType.MASTER_CHIEF),
	KRATOS("Kratos","sprites/kratos.png", 4, 4, CrosshairType.RED, MeleeWeaponType.ESPADA, HeadType.KRATOS);

	private final String name;
	private final String path;
	private final int idleLength;
	private final int walkLength;
	private final CrosshairType crosshair;
	private final Weapon weapon;
	private final HeadType head;

	PlayerType(String name, String path, int idleLength, int walkLength, CrosshairType crosshair, Weapon weapon,
			HeadType head) {
		this.name = name;
		this.path = path;
		this.idleLength = idleLength;
		this.walkLength = walkLength;
		this.crosshair = crosshair;
		this.weapon = weapon;
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
	public Weapon getWeapon() {
		return this.weapon;
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
