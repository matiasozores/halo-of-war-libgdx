package com.haloofwar.interfaces;

import com.haloofwar.common.enumerators.BulletType;
import com.haloofwar.engine.entity.EntityDescriptor;
import com.haloofwar.game.components.Component;

public interface Weapon extends EntityDescriptor {
	Component createComponent();
	String getDescription();
	int getPrice();
	BulletType getBulletType();
}
