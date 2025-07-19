package com.haloofwar.screens.settings;

import com.haloofwar.screens.Menu;
import com.haloofwar.utilities.Resources;
import com.haloofwar.utilities.Text;

public class GraphicsScreen extends Menu {

	public GraphicsScreen() {
		super(new Text[] {
			new Text("Resolución"),
			new Text("Volver al menú principal")
		});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: // Resolución
				Resources.getGame().setScreen(new ResolutionScreen());
				break;
			case 1: // Calidad de gráficos
				System.out.println("Configuración de calidad de gráficos seleccionada.");
				break;
			case 2: // Volver al menú principal
				Resources.getGame().setScreen(new SettingsScreen());
				break;
			default:
				break;
		}
	}

}
