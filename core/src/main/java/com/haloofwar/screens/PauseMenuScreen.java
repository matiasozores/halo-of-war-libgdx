package com.haloofwar.screens;

import com.haloofwar.utilities.Resources;
import com.haloofwar.utilities.Text;

public class PauseMenuScreen extends Menu{

	public PauseMenuScreen() {
		super(new Text[] {
				new Text("Reanudar"),
				new Text("Guardar y salir")
		});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
			case 0: 
				Resources.getGame().setScreen(new GameScreen());
				break;
			case 1: 
				System.out.println("Guardando y saliendo...");
				break;
			default:
				break;
		}
	}

}
