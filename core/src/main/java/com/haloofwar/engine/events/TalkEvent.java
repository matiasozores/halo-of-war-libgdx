package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.HeadType;

public class TalkEvent {
	public String[] texts;
	public HeadType avatar;
	
	public TalkEvent(String[] texts, HeadType avatar) {
		this.texts = texts;
		this.avatar = avatar;
	}
}
