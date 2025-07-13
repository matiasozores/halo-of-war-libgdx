package com.haloofwar.screens.settings;

import com.haloofwar.screens.MainMenuScreen;
import com.haloofwar.screens.Menu;
import com.haloofwar.utilities.Resources;
import com.haloofwar.utilities.Text;

public class SettingsScreen extends Menu{

	public SettingsScreen() {
		super(new Text[] {
			new Text("Gráficos", 16),
			new Text("Sonido", 16),
			new Text("Controles", 16),
			new Text("Volver al menú principal", 16)
		}, 100, 400);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				Resources.getGame().setScreen(new GraphicsScreen());
				break;
			case 1: 
				Resources.getGame().setScreen(new SoundScreen());
				break;
			case 2: 
				Resources.getGame().setScreen(new ControlsScreen());
				break;
			case 3: 
				Resources.getGame().setScreen(new MainMenuScreen());
				break;
			default:
				break;
		}
	}
}
