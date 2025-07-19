package com.haloofwar.screens.settings;

import com.haloofwar.screens.Menu;
import com.haloofwar.utilities.Resources;
import com.haloofwar.utilities.Text;

public class SoundScreen extends Menu {

	public SoundScreen() {
		super(new Text[] {
			new Text("Volumen de música"),
			new Text("Volumen de efectos"),
			new Text("Volver al menú de configuración")
		});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: // Volumen de música
				System.out.println("Ajustando volumen de música.");
				break;
			case 1: // Volumen de efectos
				System.out.println("Ajustando volumen de efectos.");
				break;
			case 2: // Volver al menú de configuración
				Resources.getGame().setScreen(new SettingsScreen());
				break;
			default:
				break;
		}
	}

}
