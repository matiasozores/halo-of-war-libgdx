package com.haloofwar.screens;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.game.GameFlowManager;

public class PauseMenuScreen extends Menu {
	private GameFlowManager flow;
	private GameManager manager;
	
	public PauseMenuScreen(GameContext context, GameManager manager) {
		super(context, "Menu Pausa", new String[] {
			"Reanudar",
			"Configuracion",
			"Guardar y volver al menu principal"
		}, null);
		
		this.flow = manager.getFlowManager();
		this.manager = manager;
	}
	
	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0:
				this.goBack();
				break;
				
			case 1:
				this.context.getGame().setScreen(new SettingsScreen(this.context, this));
				break;

			case 2: 
				this.context.getAudio().getMusic().stop();
				this.context.getGame().setScreen(new MainMenuScreen(this.context));
				this.manager.reset();
				break;

			default:
				break;
		}
	}
	
	@Override
	protected void goBack() {
		if (this.flow.currentState == GameState.PAUSED) {
			this.flow.currentState = GameState.PLAYING;
			this.context.getGame().setScreen(this.manager);
		} 
	}
}
