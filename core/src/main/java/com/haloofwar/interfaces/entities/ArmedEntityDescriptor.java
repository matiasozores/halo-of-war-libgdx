package com.haloofwar.interfaces.entities;

import com.haloofwar.enumerators.entities.WeaponType;

public interface ArmedEntityDescriptor extends AnimatedEntityDescriptor{
	WeaponType getDefaultWeapon();
}
