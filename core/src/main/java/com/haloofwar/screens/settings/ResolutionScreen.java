package com.haloofwar.screens.settings;

import com.haloofwar.screens.Menu;
import com.haloofwar.utilities.Resources;
import com.haloofwar.utilities.Text;

public class ResolutionScreen extends Menu{
	
	public ResolutionScreen() {
		super(new Text[] {
			new Text("1280x720"),
			new Text("1600x900"),
			new Text("1920x1080"),
			new Text("Volver al menú principal")
		});
	}
	
	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				System.out.println("Resolución 1280x720 seleccionada.");
				Resources.getGame().setResolution(1280, 720);
				break;
			case 1:
				System.out.println("Resolución 1600x900 seleccionada.");
				Resources.getGame().setResolution(1600, 900);
				break;
			case 2: 
				System.out.println("Resolución 1920x1080 seleccionada.");
				Resources.getGame().setResolution(1920, 1080);
				break;
			case 3:
				Resources.getGame().setScreen(new SettingsScreen());
				break;
			default:
				break;
		}
	}
}
