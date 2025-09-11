package com.haloofwar.enumerators;

import com.haloofwar.interfaces.ArmedEntityDescriptor;
import com.haloofwar.interfaces.Weapon;

public enum EnemyType implements ArmedEntityDescriptor {
	ELITE("Elite", "sprites/elite.png", 2, 8, FireArmType.PISTOLA);

	private final String name;
	private final String path;
	private final int idleLength;
	private final int walkLength;
	private final Weapon weapon;

	private EnemyType(String name, String path, int idleLength, int walkLength, Weapon weapon) {
		this.name = name;
		this.path = path;
		this.idleLength = idleLength;
		this.walkLength = walkLength;
		this.weapon = weapon;
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
	public String getName() {
		return this.name;
	}

	@Override
	public Weapon getWeapon() {
		return this.weapon;
	}
}
