package com.haloofwar.game.components;

import com.haloofwar.common.enumerators.NPCType;

public class VillagerComponent implements Component{
	public final NPCType npcType;

	public VillagerComponent(NPCType npcType) {
		this.npcType = npcType;
	}
}
