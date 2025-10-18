package com.haloofwar.engine.events;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.common.enumerators.PlayerType;

public class TalkEvent {
	public final PlayerType playerType;
	public String[] texts;
	public Texture avatar;
	
	public TalkEvent(PlayerType playerType, String[] texts, Texture avatar) {
		this.playerType = playerType;
		this.texts = texts;
		this.avatar = avatar;
	}
}
