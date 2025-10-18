package com.haloofwar.engine.interfaces;

import com.haloofwar.common.enumerators.Direction;
import com.haloofwar.engine.entity.Entity;

public interface MovementController {
	float getDirectionX();
	float getDirectionY();

	default void update(float delta) {}
	void changeTarget(Entity entity);
	void onUpdatePosition(Direction direction, boolean isPressed);
	void setMove(boolean canMove);
}
