package com.haloofwar.screens.settings;

import com.haloofwar.screens.Menu;
import com.haloofwar.utilities.GameContext;
import com.haloofwar.utilities.Text;

public class SoundScreen extends Menu {

	public SoundScreen(GameContext gameContext) {
		super(gameContext, new Text[] {
			new Text("Volumen de música"),
			new Text("Volumen de efectos"),
			new Text("Volver al menú de configuración")
		});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				System.out.println("Ajustando volumen de música.");
				break;
			case 1: 
				System.out.println("Ajustando volumen de efectos.");
				break;
			case 2: 
				this.gameContext.getGame().setScreen(new SettingsScreen(this.gameContext));
				break;
			default:
				break;
		}
	}

}
