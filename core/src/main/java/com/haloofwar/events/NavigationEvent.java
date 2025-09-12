package com.haloofwar.events;

import com.haloofwar.enumerators.Direction;

public class NavigationEvent {	
	public Direction direction;
	public boolean isPressed;

	public NavigationEvent(Direction direction, boolean isPressed) {
		this.direction = direction;
		this.isPressed = isPressed;
	}
}
