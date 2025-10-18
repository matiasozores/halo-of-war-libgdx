package com.haloofwar.engine.events.online;

public class StartGameEventOnline {
	public final int kratosId;
	public final int masterchiefId;
	
	public StartGameEventOnline(int kratosId, int masterchiefId) {
		this.kratosId = kratosId;
		this.masterchiefId = masterchiefId;
	}
}
