package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.components.Entity;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.PlayerType;
import com.haloofwar.enumerators.SoundType;

public class PlayerSelectionScreen extends Menu{
	private final int MASTER_CHIEF_OPTION = 0;
	private final int KRATOS_OPTION = 1;
	private final int BACK_OPTION = 2;
	
	public PlayerSelectionScreen(GameContext gameContext, Screen previousScreen) {
		super(gameContext, "Seleccion de jugador", new String[] {
			"Kratos",
			"Master Chief",
			"Volver"
		}, previousScreen);
	}

	@Override
	protected void processOption(int optionIndex) {
		PlayerType typeChoice = PlayerType.KRATOS; 
		
		switch (optionIndex) {
			case KRATOS_OPTION:
				typeChoice = PlayerType.MASTER_CHIEF; 
				break;
				
			case MASTER_CHIEF_OPTION:
				typeChoice = PlayerType.KRATOS; 
				break;

			case BACK_OPTION: 
				this.goBack();
				break;

			default:
				typeChoice = PlayerType.KRATOS; 
				break;
		}
		
		if(optionIndex == this.BACK_OPTION) {
			return;
		}
		
		Entity player = this.context.getFactories().getPLAYER_FACTORY().create(typeChoice, 0, 0);
		this.context.getGameplay().initializePlayer(player);

		this.context.getAudio().getSound().play(SoundType.LOAD_GAME);
		this.context.getAudio().getMusic().stop();
		this.context.getGame().setScreen(new GameManager(this.context));
	}
}
