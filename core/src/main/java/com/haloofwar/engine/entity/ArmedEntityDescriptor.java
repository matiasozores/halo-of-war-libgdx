package com.haloofwar.engine.entity;

import com.haloofwar.interfaces.Weapon;

public interface ArmedEntityDescriptor extends AnimatedEntityDescriptor{
	Weapon getWeapon();
}
