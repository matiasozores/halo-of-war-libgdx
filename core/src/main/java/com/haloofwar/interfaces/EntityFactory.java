package com.haloofwar.interfaces;

import com.haloofwar.components.Entity;

public interface EntityFactory {
	Entity create(EntityDescriptor type, float x, float y);
}
