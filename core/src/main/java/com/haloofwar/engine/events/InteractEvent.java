package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.PlayerType;

public class InteractEvent {
	public PlayerType playerType;
    private final boolean pressed;

    public InteractEvent(PlayerType playerType, boolean pressed) {
        this.pressed = pressed;
        this.playerType = playerType;
    }

    public boolean isPressed() {
        return this.pressed;
    }
}
