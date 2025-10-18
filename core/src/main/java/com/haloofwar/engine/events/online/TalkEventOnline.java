package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.NPCType;
import com.haloofwar.common.enumerators.PlayerType;

public class TalkEventOnline {
	public final PlayerType playerType;
	public final NPCType npcType;

	public TalkEventOnline(PlayerType playerType, NPCType npcType) {
		this.playerType = playerType;
		this.npcType = npcType;
	}
}
