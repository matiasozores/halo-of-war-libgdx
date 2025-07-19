package com.haloofwar.screens;

import com.haloofwar.screens.settings.SettingsScreen;
import com.haloofwar.utilities.Resources;
import com.haloofwar.utilities.Text;

public class MainMenuScreen extends Menu{

	public MainMenuScreen() {
		super(new Text[] {
			new Text("Jugar"),
			new Text("Configuracion"),
			new Text("Salir")
		});
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: // Jugar
				Resources.getGame().setScreen(new GameScreen());
				break;
			case 1: // Opciones
				Resources.getGame().setScreen(new SettingsScreen());
				break;
			case 2: // Salir
				Resources.getGame().dispose();
				System.exit(0);
				break;
			default:
				break;
		}
	}

}
