package com.haloofwar.game.components;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.engine.components.Component;

public class DialogueComponent implements Component {
	public String[] lines;
	public int currentLine;
	public Texture avatar;
	
    public DialogueComponent(String[] lines, Texture avatar) {
        this.lines = lines;
        this.currentLine = 0;
        this.avatar = avatar;
    }
}
