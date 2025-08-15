package com.haloofwar.interfaces.entities;

import com.haloofwar.enumerators.animation.HeadType;
import com.haloofwar.enumerators.entities.CrosshairType;

public interface CrosshairEntityDescriptor extends ArmedEntityDescriptor {
	CrosshairType getCrosshair();
	HeadType getHead();
}
