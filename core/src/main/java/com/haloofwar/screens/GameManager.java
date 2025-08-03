package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.players.Player;
import com.haloofwar.enumerators.game.GameState;
import com.haloofwar.enumerators.game.SceneType;
import com.haloofwar.enumerators.game.SoundType;
import com.haloofwar.game.GameFlowManager;

public class GameManager implements Screen {
	private final GameContext context;
	private final Player player;
	
	private final GameFlowManager flowManager;
	private final PauseMenuScreen pauseMenu;
	
	// Cooldown temporal para morir
	private final int DEATH_COOLDOWN = 30; 
	private int deathCooldown = this.DEATH_COOLDOWN;
	// ----------------------------------------------------

	public GameManager(GameContext context, Player player) {
		this.context = context;
		this.player = player;
		
		this.pauseMenu = new PauseMenuScreen(context, this);
		
		this.flowManager = new GameFlowManager(context);
		this.flowManager.startGame(this.player, SceneType.TUTORIAL);
	}

	@Override
	public void show() {
		this.flowManager.getCurrentScene().show();
	}

	@Override
	public void render(float delta) {
		// Temporal para saber que pasa si el jugador muere
		if(this.context.getInput().isOpenInventory() && this.deathCooldown <= 0) {
			this.deathCooldown = this.DEATH_COOLDOWN;
			this.player.takeDamage(10);
			
			if (!this.player.isActive()) {
				// A futuro cuando se muera el jugador tendriamos que disparar un evento para 
				// cambiar el estado del juego
				this.flowManager.setGameState(GameState.GAME_OVER);
			}
		} else {
			this.deathCooldown--;
		}
		
		// ----------------------------------------------------
		
		this.flowManager.update(delta);
		
		GameState state = this.flowManager.getGameState();

		switch (state) {
			case PAUSED:
				this.context.getGame().setScreen(this.pauseMenu);
				return;
			case GAME_OVER:
				this.context.getAudio().getMusic().stop();
				this.context.getAudio().getSound().stopAll();
				this.context.getAudio().getSound().play(SoundType.GAME_OVER);
				this.context.getGame().setScreen(new GameOverScreen(this.context));
				return;
			case PLAYING:
			case WAITING:
				this.flowManager.render(delta);
				break;
		}
	}

	public void reset() {
		this.context.getGameplay().dispose();
	}

	@Override
	public void resize(int width, int height) {
		this.flowManager.getCurrentScene().resize(width, height);
		this.pauseMenu.resize(width, height);
	}

	@Override
	public void pause() {
		this.flowManager.getCurrentScene().pause();
	}

	@Override
	public void resume() {
		this.flowManager.getCurrentScene().resume();
	}

	@Override
	public void hide() {
		this.flowManager.getCurrentScene().hide();
	}

	@Override
	public void dispose() {
		this.flowManager.getCurrentScene().dispose();
	}

	// Getters para otras clases
	public GameFlowManager getFlowManager() {
		return this.flowManager;
	}
}
