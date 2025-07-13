package com.haloofwar.screens;

import java.util.Scanner;

import com.badlogic.gdx.Screen;
import com.haloofwar.entities.characters.Kratos;
import com.haloofwar.entities.characters.MasterChief;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.world.Level;
import com.haloofwar.world.Lobby;

public class GameScreen implements Screen {
	
	// Debugging purposes
	private Scanner scanner = new Scanner(System.in);

	private Player player1;
	private Level level;
	
	@Override
	public void show() {
		/*
		System.out.println("Elija con quien jugar: ");
		System.out.println("1. Kratos");
		System.out.println("2. Master Chief");
		*/
		int opc = 1;
		
		switch (opc) {
			case 1:
				this.player1 = new Kratos();
				break;
			case 2:
				this.player1 = new MasterChief();
				break;
			default:
				this.player1 = new Kratos();
				break;
		}
		System.out.println("" + player1.getClass().getSimpleName() + " seleccionado como jugador 1.");
		this.level = new Lobby(player1);
		this.level.show();
	}

	@Override
	public void render(float delta) {
		this.update();
		
		this.level.render(delta);
	}

	public void update() {
		this.level.update();
	}
	
	@Override
	public void resize(int width, int height) {
		// Code to handle resizing of the game screen
	}

	@Override
	public void pause() {
		// Code to pause the game screen
	}

	@Override
	public void resume() {
		// Code to resume the game screen
	}

	@Override
	public void hide() {
		// Code to hide the game screen
	}

	@Override
	public void dispose() {
		// Code to dispose of resources used by the game screen
	}

}
