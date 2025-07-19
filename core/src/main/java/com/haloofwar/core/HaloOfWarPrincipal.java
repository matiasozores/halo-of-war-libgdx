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
    	this.inputManager = new InputManager();
    	Resources.setInputManager(this.inputManager);
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
	}
}
