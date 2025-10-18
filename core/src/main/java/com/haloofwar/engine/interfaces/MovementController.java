package com.haloofwar.engine.interfaces;

import com.haloofwar.engine.entity.Entity;

public interface MovementController {
	float getDirectionX();
	float getDirectionY();
	void setDirection(float x, float y);
	default void update(float delta) {}
	void changeTarget(Entity entity);
}
