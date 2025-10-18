package com.haloofwar.engine.events;

public class RemoveEntityByIdentifierEvent {
	public final int identifier;

	public RemoveEntityByIdentifierEvent(int identifier) {
		this.identifier = identifier;
	}
}
