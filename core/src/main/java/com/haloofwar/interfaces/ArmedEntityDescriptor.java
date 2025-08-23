package com.haloofwar.interfaces;

import com.haloofwar.enumerators.WeaponType;

public interface ArmedEntityDescriptor extends AnimatedEntityDescriptor{
	WeaponType getDefaultWeapon();
}
