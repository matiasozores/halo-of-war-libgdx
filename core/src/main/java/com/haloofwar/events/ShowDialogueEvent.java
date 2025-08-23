package com.haloofwar.events;

import com.badlogic.gdx.graphics.Texture;

public class ShowDialogueEvent {
    private final String text;
    private Texture avatar;

    public ShowDialogueEvent(String text) {
        this.text = text;
    }
    
    public ShowDialogueEvent(String text, Texture avatar) {
        this.text = text;
        this.avatar = avatar;
    }

    public String getText() {
        return this.text;
    }
    
    public Texture getAvatar() {
		return this.avatar;
	}
}
