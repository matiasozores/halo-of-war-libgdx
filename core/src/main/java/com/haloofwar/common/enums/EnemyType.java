package com.haloofwar.common.enums;

import com.haloofwar.engine.entity.ArmedEntityDescriptor;
import com.haloofwar.interfaces.Weapon;

public enum EnemyType implements ArmedEntityDescriptor {
	ELITE("Elite", "sprites/elite.png", 2, 8, FireArmType.ESCOPETA),
	GRUNT("Grunt", "sprites/grunt.png", 2, 8, FireArmType.PISTOLA),
	JACKAL("Jackal", "sprites/jackal.png", 2, 8, FireArmType.REVOLVER),
	HUNTER("Hunter", "sprites/hunter.png", 2, 8, FireArmType.REVOLVER),
	YANME("Yanme", "sprites/yanme.png", 2, 8, FireArmType.SUBFUSIL),
	HELIOS("Helios", "sprites/helios.png", 2, 8, FireArmType.AMETRALLADORA),
	POSEIDON("Poseidon", "sprites/poseidon.png", 2, 8, FireArmType.ESCOPETA),
	HADES("Hades", "sprites/hades.png", 2, 8, FireArmType.FRANCO),
	ZEUS("Zeus", "sprites/zeus.png", 2, 8, FireArmType.FRANCO),
	HERMES("Hermes", "sprites/hermes.png", 2, 8, FireArmType.SUBFUSIL);

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
