package com.haloofwar.dependences;

import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.core.HaloOfWarPrincipal;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.audio.AudioManager;
import com.haloofwar.dependences.gameplay.GameplayContext;
import com.haloofwar.dependences.graphics.RenderContext;
import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.ecs.events.EventBus;

public class GameContext {
	private final HaloOfWarPrincipal game;

	private final TextureManager texture;
	private final AudioManager audio;
	private final RenderContext render;
	
	private final EventBus bus;
	private final InputManager input;

	private final GameStaticCamera staticCamera;
	private final GameWorldCamera worldCamera;

	private final GameplayContext gameplay;
	
	public GameContext(HaloOfWarPrincipal game) {
		this.game = game;
		
		this.texture = new TextureManager();
		this.audio = new AudioManager();
		this.render = new RenderContext();

		this.bus = new EventBus();
		this.input = new InputManager(this.bus);

		this.staticCamera = new GameStaticCamera();
		this.worldCamera = new GameWorldCamera();

		this.gameplay = new GameplayContext(
			this.render.getBatch(),
			this.input,
			this.audio.getSound(),
			this.texture,
			this.bus
		);
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

	public RenderContext getRender() {
		return this.render;
	}

	public InputManager getInput() {
		return this.input;
	}

	public GameStaticCamera getStaticCamera() {
		return this.staticCamera;
	}

	public GameWorldCamera getWorldCamera() {
		return this.worldCamera;
	}
	
	public EventBus getBus() {
		return this.bus;
	}
	
	public GameplayContext getGameplay() {
		return this.gameplay;
	}
	
	public void dispose() {
		this.disposeScene();
		this.render.dispose();
		this.texture.dispose();
		this.audio.dispose();
	}

	public void disposeScene() {
		this.gameplay.dispose();
	}
}
