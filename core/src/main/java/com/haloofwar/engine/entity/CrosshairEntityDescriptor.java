package com.haloofwar.engine.entity;

import com.haloofwar.common.enumerators.CrosshairType;
import com.haloofwar.common.enumerators.HeadType;

public interface CrosshairEntityDescriptor extends ArmedEntityDescriptor {
	CrosshairType getCrosshair();
	HeadType getHead();
}
