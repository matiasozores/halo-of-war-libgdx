package com.haloofwar.dependences;

import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.core.HaloOfWarPrincipal;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.audio.AudioManager;
import com.haloofwar.dependences.gameplay.GameplayContext;
import com.haloofwar.dependences.graphics.RenderContext;
import com.haloofwar.dependences.input.InputManager;

public class GameContext {
	private final HaloOfWarPrincipal game;

	private final TextureManager texture;
	private final AudioManager audio;
	private final RenderContext render;
	private final InputManager input;
	
	private final GameStaticCamera staticCamera;
	private final GameWorldCamera worldCamera;
	
	private final GameplayContext gameplay;
	
	public GameContext(HaloOfWarPrincipal game) {
		this.game = game;
		this.texture = new TextureManager();
		this.audio = new AudioManager();
		this.render = new RenderContext();
		this.input = new InputManager();
		
		this.staticCamera = new GameStaticCamera();
		this.worldCamera = new GameWorldCamera();
		this.gameplay = new GameplayContext(this.input);
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
	
	// Camaras
	
	public GameStaticCamera getStaticCamera() {
		return this.staticCamera;
	}
	
	public GameWorldCamera getWorldCamera() {
		return this.worldCamera;
	}
	
	public void dispose() {
		this.texture.dispose();
		this.audio.dispose();
	}
	
	// Gameplay (no crear mas instancias de esto sino que reutilizar la misma)
	
	public GameplayContext getGameplay() {
		return this.gameplay;
	}
}
