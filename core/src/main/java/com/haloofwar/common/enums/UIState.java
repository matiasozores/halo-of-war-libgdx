package com.haloofwar.common.enums;

import java.util.ArrayList;

public enum UIState {
	NONE,
	INVENTORY,
	SHOP,
	EQUIPMENT;
	
	public static UIState[] getBlockedStates(final UIState notBlocked) {
	    final ArrayList<UIState> states = new ArrayList<>();
	    for (UIState state : UIState.values()) {
	        if (!notBlocked.equals(state) && !state.equals(NONE)) {
	            states.add(state);
	        }
	    }
	    return states.toArray(new UIState[0]);
	}

}
