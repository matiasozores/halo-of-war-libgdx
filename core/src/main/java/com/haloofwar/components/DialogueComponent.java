package com.haloofwar.components;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.interfaces.Component;

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
