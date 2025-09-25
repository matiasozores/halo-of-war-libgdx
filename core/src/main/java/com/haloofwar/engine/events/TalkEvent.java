package com.haloofwar.engine.events;

import com.badlogic.gdx.graphics.Texture;

public class TalkEvent {
	public String[] texts;
	public Texture avatar;
	
	public TalkEvent(String[] texts, Texture avatar) {
		this.texts = texts;
		this.avatar = avatar;
	}
}
