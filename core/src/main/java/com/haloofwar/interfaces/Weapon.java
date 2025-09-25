package com.haloofwar.interfaces;

import com.haloofwar.engine.components.Component;
import com.haloofwar.engine.entity.EntityDescriptor;

public interface Weapon extends EntityDescriptor {
	Component createComponent();
	String getDescription();
	int getPrice();
}
