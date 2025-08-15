package com.haloofwar.interfaces.factories;

import com.haloofwar.ecs.Entity;
import com.haloofwar.interfaces.entities.EntityDescriptor;

public interface EntityFactory {
	Entity create(EntityDescriptor type, float x, float y);
}
