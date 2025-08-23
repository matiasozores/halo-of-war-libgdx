package com.haloofwar.enumerators;

import com.haloofwar.interfaces.Talkable;

public enum NPCType implements Talkable {
	VILLAGER("Aldeano", "sprites/elite.png", 2, 8, new String[] {"Hola viajero!", "Quieres comprar algo?", "Buena suerte en tu recorrido!"}, HeadType.MASTER_CHIEF);

	private String name;
	private String path;
	private int idleLength;
	private int walkLength;
	private String[] dialogues;
	private HeadType head;
	
	private NPCType(String name, String path, int idleLength, int walkLength, String[] dialogues, HeadType head) {
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
