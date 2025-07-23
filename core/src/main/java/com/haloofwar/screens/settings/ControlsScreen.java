package com.haloofwar.screens.settings;

import com.haloofwar.screens.Menu;
import com.haloofwar.utilities.GameContext;
import com.haloofwar.utilities.Text;

public class ControlsScreen extends Menu {

	public ControlsScreen(GameContext gameContext) {
		super(gameContext, new Text[] {
			new Text("Moverse hacia arriba"),
			new Text("Moverse hacia abajo"),
			new Text("Moverse hacia la izquierda"),
			new Text("Moverse hacia la derecha"),
			new Text("Atacar"),
			new Text("Volver al menú de configuración")
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
