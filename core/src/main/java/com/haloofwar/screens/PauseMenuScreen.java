package com.haloofwar.screens;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.GameStateEvent;
import com.haloofwar.saves.SaveManager;

public class PauseMenuScreen extends Menu {
	private GameManager manager;
	private EventBus bus;
	
	public PauseMenuScreen(GameContext context, GameManager manager) {
		super(context, "Menu Pausa", new String[] {
			"Reanudar",
			"Configuracion",
			"Guardar y volver al menu principal"
		}, null);

		this.bus = context.getBus();
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
				SaveManager.saveGame(this.context.getGameplay().getPlayer(), this.manager.getFlowManager().getCompletedLevels());
				this.context.getAudio().getMusic().stop();
				this.manager.reset();
				this.context.getGame().setScreen(new MainMenuScreen(this.context));
				break;
			default:
				break;
		}
	}
	
	@Override
	protected void goBack() {
		this.context.getGame().setScreen(this.manager);
		this.bus.publish(new GameStateEvent(GameState.PLAYING));
	}
}
