package com.haloofwar.common.enumerators;

import com.haloofwar.engine.entity.ArmedEntityDescriptor;
import com.haloofwar.engine.utils.RandomUtils;
import com.haloofwar.interfaces.Weapon;

public enum EnemyType implements ArmedEntityDescriptor {
	ELITE_INOFENSIVO("Elite", "sprites/elite_inofensivo.png", 2, 8, 0f, 5f),
	ELITE("Elite", "sprites/elite.png", 2, 8, FireArmType.ESCOPETA, 0f, 5f),
	GRUNT("Grunt", "sprites/grunt.png", 2, 8, FireArmType.PISTOLA, 3f, 5f),
	JACKAL("Jackal", "sprites/jackal.png", 2, 8, FireArmType.REVOLVER, 3f, 5f),
	HUNTER("Hunter", "sprites/hunter.png", 2, 8, FireArmType.REVOLVER, 3f, 5f),
	YANME("Yanme", "sprites/yanme.png", 2, 8, FireArmType.SUBFUSIL, 3f, 5f),
	HELIOS("Helios", "sprites/helios.png", 2, 8, FireArmType.AMETRALLADORA, 3f, 5f),
	POSEIDON("Poseidon", "sprites/poseidon.png", 2, 8, FireArmType.ESCOPETA, 3f, 5f),
	HADES("Hades", "sprites/hades.png", 2, 8, FireArmType.FRANCO, 3f, 5f),
	ZEUS("Zeus", "sprites/zeus.png", 2, 8, FireArmType.FRANCO, 3f, 5f),
	HERMES("Hermes", "sprites/hermes.png", 2, 8, FireArmType.SUBFUSIL, 3f, 5f);

	private final String name;
	private final String path;
	private final int idleLength;
	private final int walkLength;
	private Weapon weapon;
	private float minShootTimer, maxShootTimer;
	
	private EnemyType(final String name, final String path, final int idleLength, final int walkLength, final Weapon weapon, float minShootTimer, float maxShootTimer) {
		this.name = name;
		this.path = path;
		this.idleLength = idleLength;
		this.walkLength = walkLength;
		this.weapon = weapon;
		this.minShootTimer = minShootTimer;
		this.maxShootTimer = maxShootTimer;
	}
	
	private EnemyType(final String name, final String path, final int idleLength, final int walkLength, float minShootTimer, float maxShootTimer) {
		this.name = name;
		this.path = path;
		this.idleLength = idleLength;
		this.walkLength = walkLength;
		this.minShootTimer = minShootTimer;
		this.maxShootTimer = maxShootTimer;
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
	
	public static EnemyType getEnemyByCollection(EnemyType[] enemies) {
		if(enemies == null) {
			System.out.println("Ha ocurrido un problema a la hora de generar un enemigo mediante una collecion... | EnemyType");
			return null;
		}
		
		return enemies[RandomUtils.generateInt(enemies.length)];
	}
	
	public float getMinShootTimer() {
		return this.minShootTimer;
	}
	
	public float getMaxShootTimer() {
		return this.maxShootTimer;
	}
}
