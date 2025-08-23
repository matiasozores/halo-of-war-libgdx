package com.haloofwar.dependences;

import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.core.HaloOfWarPrincipal;
import com.haloofwar.events.EventBus;
import com.haloofwar.factories.components.FactoryCollection;

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
	private final FactoryCollection factories;
	
	public GameContext(HaloOfWarPrincipal game) {
		this.game = game;
		
		this.texture = new TextureManager();
		this.audio = new AudioManager();
		this.render = new RenderContext();

		this.bus = new EventBus();
	
		this.staticCamera = new GameStaticCamera();
		this.worldCamera = new GameWorldCamera();
		
		this.factories = new FactoryCollection(this);
		
		this.gameplay = new GameplayContext(
				this.render.getBatch(),
				this.audio.getSound(),
				this.texture,
				this.bus
			);
		
		this.input = new InputManager(this.bus);
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
	
	public FactoryCollection getFactories() {
		return this.factories;
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
