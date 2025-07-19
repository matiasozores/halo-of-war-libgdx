package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.entities.characters.Kratos;
import com.haloofwar.entities.characters.MasterChief;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.world.levels.Level;
import com.haloofwar.world.levels.Lobby;

public class GameScreen implements Screen {
	
	private Player player1;
	private Level level;
	
	@Override
	public void show() {

		// Para probar, esto deberia ser una seleccion de personaje
		int opc = 2;
		
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
