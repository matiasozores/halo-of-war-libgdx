package com.haloofwar.screens.settings;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.Menu;

public class ControlsScreen extends Menu {

	public ControlsScreen(GameContext gameContext) {
		super(gameContext, new String[] {
				"Nada",
				"Volver",
			});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				System.out.println("Configuración de movimiento hacia arriba seleccionada.");
				break;
			case 1: 
				System.out.println("Configuración de movimiento hacia abajo seleccionada.");
				break;
			case 2: 
				System.out.println("Configuración de movimiento hacia la izquierda seleccionada.");
				break;
			case 3:
				System.out.println("Configuración de movimiento hacia la derecha seleccionada.");
				break;
			case 4:
				System.out.println("Configuración de ataque seleccionada.");
				break;
			case 5:
				this.gameContext.getGame().setScreen(new SettingsScreen(this.gameContext));
				break;
			default:
				break;
		}
	}

}
