package com.haloofwar.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.haloofwar.input.InputManager;
import com.haloofwar.screens.MainMenuScreen;
import com.haloofwar.utilities.Resources;

public class HaloOfWarPrincipal extends Game {
    private InputManager inputManager;

    @Override
    public void create() {
    	Resources.setGame(this);
    	this.inputManager = Resources.getInputManager();
    	Gdx.input.setInputProcessor(this.inputManager);
    	Gdx.input.setCursorCatched(true);
        this.setScreen(new MainMenuScreen());
    }

    @Override
    public void render() {
    	Resources.cleanWindow();
    	super.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
   
    }
    
    public void setResolution(int width, int height) {
		Gdx.graphics.setWindowedMode(width, height);
		Gdx.graphics.setResizable(false);
		Gdx.graphics.setTitle("Halo Of War - " + width + "x" + height);
	}
}
