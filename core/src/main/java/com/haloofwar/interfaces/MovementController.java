package com.haloofwar.interfaces;

public interface MovementController {
	float getDirectionX();
	float getDirectionY();
	default void update(float delta) {}
}
