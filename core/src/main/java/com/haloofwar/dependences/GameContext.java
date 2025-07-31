package com.haloofwar.dependences;

import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.core.HaloOfWarPrincipal;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.audio.AudioManager;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.dependences.gameplay.GameplayContext;
import com.haloofwar.dependences.graphics.RenderContext;
import com.haloofwar.dependences.input.InputManager;

public class GameContext {
	private final HaloOfWarPrincipal game;

	private final TextureManager texture;
	private final AudioManager audio;
	private final CollisionManager collision;
	private final RenderContext render;
	private final InputManager input;
	private final GameStaticCamera staticCamera;
	private final GameplayContext gameplay;
	
	public GameContext(HaloOfWarPrincipal game) {
		this.game = game;
		this.texture = new TextureManager();
		this.audio = new AudioManager();
		this.collision = new CollisionManager();
		this.render = new RenderContext();
		this.input = new InputManager();

		this.staticCamera = new GameStaticCamera();
		this.gameplay = new GameplayContext();
	}
	
	public HaloOfWarPrincipal getGame() {
		return this.game;
	}
	
	public TextureManager getTexture() {
		return this.texture;
	}
	
	public AudioManager getAudio() {
		return this.audio;
	}
	
	public CollisionManager getCollision() {
		return this.collision;
	}
	
	public RenderContext getRender() {
		return this.render;
	}
	
	public InputManager getInput() {
		return this.input;
	}
	
	public GameStaticCamera getStaticCamera() {
		return this.staticCamera;
	}
	
	public GameplayContext getGameplay() {
		return this.gameplay;
	}
	
	public void dispose() {
		this.texture.dispose();
		this.audio.dispose();
		this.collision.clear();
		this.gameplay.dispose();
	}
}
