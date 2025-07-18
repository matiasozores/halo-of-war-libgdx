package com.haloofwar.screens.settings;

import com.haloofwar.screens.Menu;
import com.haloofwar.utilities.Resources;
import com.haloofwar.utilities.Text;

public class ResolutionScreen extends Menu{
	
	public ResolutionScreen() {
		super(new Text[] {
			new Text("1280x720", 16),
			new Text("1600x900", 16),
			new Text("1920x1080", 16),
			new Text("Volver al menú principal", 16)
		}, 100, 400);
	}
	
	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: // 1280x720
				System.out.println("Resolución 1280x720 seleccionada.");
				Resources.getGame().setResolution(1280, 720);
				break;
			case 1: // 1600x900
				System.out.println("Resolución 1600x900 seleccionada.");
				Resources.getGame().setResolution(1600, 900);
				break;
			case 2: // 1920x1080
				System.out.println("Resolución 1920x1080 seleccionada.");
				Resources.getGame().setResolution(1920, 1080);
				break;
			case 3: // Volver al menú principal
				Resources.getGame().setScreen(new SettingsScreen());
				break;
			default:
				break;
		}
	}
}
