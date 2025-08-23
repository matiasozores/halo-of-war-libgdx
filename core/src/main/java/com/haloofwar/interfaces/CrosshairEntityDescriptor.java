package com.haloofwar.interfaces;

import com.haloofwar.enumerators.CrosshairType;
import com.haloofwar.enumerators.HeadType;

public interface CrosshairEntityDescriptor extends ArmedEntityDescriptor {
	CrosshairType getCrosshair();
	HeadType getHead();
}
