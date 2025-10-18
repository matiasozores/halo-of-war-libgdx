package com.haloofwar.common.enumerators;

import com.haloofwar.interfaces.Talkable;

public enum NPCType implements Talkable {
	VILLAGER("Aldeano", "sprites/npcs/carlos.png", 7, 2, new String[] {"Hola viajero!", "Quieres comprar algo?", "Buena suerte en tu recorrido!"}, HeadType.MASTER_CHIEF);

	private final String name;
	private final String path;
	private final int idleLength;
	private final int walkLength;
	private final String[] dialogues;
	private final HeadType head;
	
	private NPCType(
		final String name, 
		final String path, 
		final int idleLength, 
		final int walkLength, 
		final String[] dialogues, 
		final HeadType head
	) {	
		this.name = name;
		this.path = path;
		this.idleLength = idleLength;
		this.walkLength = walkLength;
		this.dialogues = dialogues;
		this.head = head;
	}

	public String getName() {
		return this.name;
	}
	
	public String getPath() {
		return this.path;
	}

	public int getIdleLength() {
		return this.idleLength;
	}

	public int getWalkLength() {
		return this.walkLength;
	}	
	
	@Override
	public String[] getLines() {
		return this.dialogues;
	}
	
	public HeadType getHead() {
		return this.head;
	}
}
