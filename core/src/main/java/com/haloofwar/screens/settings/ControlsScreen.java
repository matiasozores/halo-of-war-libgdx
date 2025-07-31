package com.haloofwar.screens.settings;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.Menu;

public class ControlsScreen extends Menu {

	public ControlsScreen(GameContext gameContext, Menu menu) {
		super(gameContext, "Controles",new String[] {
				"Nada",
				"Volver",
			}, menu);
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				System.out.println("Configuración de movimiento hacia arriba seleccionada.");
				break;

			case 1:
				this.goBack();
				break;
			default:
				break;
		}
	}

}
