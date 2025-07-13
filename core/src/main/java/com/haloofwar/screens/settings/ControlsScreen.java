package com.haloofwar.screens.settings;

import com.haloofwar.screens.Menu;
import com.haloofwar.utilities.Resources;
import com.haloofwar.utilities.Text;

public class ControlsScreen extends Menu {

	public ControlsScreen() {
		super(new Text[] {
			new Text("Moverse hacia arriba", 16),
			new Text("Moverse hacia abajo", 16),
			new Text("Moverse hacia la izquierda", 16),
			new Text("Moverse hacia la derecha", 16),
			new Text("Atacar", 16),
			new Text("Volver al menú de configuración", 16)
		}, 100, 400);
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: // Moverse hacia arriba
				System.out.println("Configuración de movimiento hacia arriba seleccionada.");
				break;
			case 1: // Moverse hacia abajo
				System.out.println("Configuración de movimiento hacia abajo seleccionada.");
				break;
			case 2: // Moverse hacia la izquierda
				System.out.println("Configuración de movimiento hacia la izquierda seleccionada.");
				break;
			case 3: // Moverse hacia la derecha
				System.out.println("Configuración de movimiento hacia la derecha seleccionada.");
				break;
			case 4: // Atacar
				System.out.println("Configuración de ataque seleccionada.");
				break;
			case 5: // Volver al menú de configuración
				Resources.getGame().setScreen(new SettingsScreen());
				break;
			default:
				break;
		}
	}

}
