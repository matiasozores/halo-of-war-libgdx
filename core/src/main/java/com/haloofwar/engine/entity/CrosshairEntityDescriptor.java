package com.haloofwar.engine.entity;

import com.haloofwar.common.enums.CrosshairType;
import com.haloofwar.common.enums.HeadType;

public interface CrosshairEntityDescriptor extends ArmedEntityDescriptor {
	CrosshairType getCrosshair();
	HeadType getHead();
}
