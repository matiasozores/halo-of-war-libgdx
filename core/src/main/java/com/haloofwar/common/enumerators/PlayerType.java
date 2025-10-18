package com.haloofwar.common.enumerators;

import com.haloofwar.engine.entity.CrosshairEntityDescriptor;
import com.haloofwar.engine.utils.RandomUtils;
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

	PlayerType(
		final String name, 
		final String path, 
		final int idleLength, 
		final int walkLength, 
		final CrosshairType crosshair, 
		final Weapon weapon,
		final HeadType head
	) {
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
	
	public static PlayerType getPlayerByName(final String NAME) {
		boolean found = false;
		PlayerType type = null;
		int i = 0;
		
		while(i < PlayerType.values().length && !found) {
			if(PlayerType.values()[i].getName().equals(NAME)) {
				found = true;
				type = PlayerType.values()[i];
			} else {
				i++;
			}
		}
		
		if(type == null) {
			System.out.println("Tipo de jugador inválido recibido: " + NAME);
		}
		
		return type;
	}
	
	public static PlayerType generate() {
		return PlayerType.values()[RandomUtils.generateInt(PlayerType.values().length)];
	}
}
