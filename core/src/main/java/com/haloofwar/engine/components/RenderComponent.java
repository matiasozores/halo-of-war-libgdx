package com.haloofwar.engine.components;

import com.badlogic.gdx.graphics.Texture;

public class RenderComponent implements Component{
	public Texture texture;
	public float angle;
	
	public RenderComponent(Texture texture) {
		this.texture = texture;
		this.angle = 0f;
	}
	
	public RenderComponent(Texture texture, float angle) {
		this.texture = texture;
		this.angle = angle;
	}
}
