package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.Direction;

public class ShopScrollEvent {
	public final Direction direction;

	public ShopScrollEvent(Direction direction) {
		this.direction = direction;
	}
	
}
