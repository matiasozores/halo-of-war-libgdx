package com.haloofwar.engine.events;

import com.haloofwar.common.enums.UIState;

public class TogglePopupEvent {
	public final UIState state;

	public TogglePopupEvent(UIState state) {
		this.state = state;
	}
}
