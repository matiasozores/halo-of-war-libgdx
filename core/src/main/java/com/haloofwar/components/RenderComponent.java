package com.haloofwar.components;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.interfaces.Component;

public class RenderComponent implements Component{
	public Texture texture;
	public float angle = 0f;
	
	public RenderComponent(Texture texture) {
		this.texture = texture;
	}
	
	public RenderComponent(Texture texture, float angle) {
		this.texture = texture;
		this.angle = angle;
	}
}
