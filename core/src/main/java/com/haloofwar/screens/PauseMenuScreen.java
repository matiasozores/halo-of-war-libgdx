package com.haloofwar.screens;

import com.haloofwar.screens.settings.SettingsScreen;
import com.haloofwar.utilities.Resources;
import com.haloofwar.utilities.Text;

public class PauseMenuScreen extends Menu{

	public PauseMenuScreen() {
		super(new Text[] {
				new Text("Reanudar", 16),
				new Text("Configuracion", 16),
				new Text("Guardar y salir", 16)
		}, 100, 400);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				Resources.getGame().setScreen(new GameScreen());
				break;
			case 1: 
				Resources.getGame().setScreen(new SettingsScreen());
				break;
			case 2: 
			
				break;
			default:
				break;
		}
	}

}
