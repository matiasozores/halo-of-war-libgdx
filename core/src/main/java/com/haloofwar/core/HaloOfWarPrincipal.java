package com.haloofwar.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.haloofwar.screens.MainMenuScreen;
import com.haloofwar.utilities.GameContext;
import com.haloofwar.utilities.Resources;

public class HaloOfWarPrincipal extends Game {
    private GameContext gameContext;
    
    @Override
    public void create() {
      	this.gameContext = new GameContext(this);
    	this.setInputManager();
        this.setScreen(new MainMenuScreen(this.gameContext));
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
    	this.gameContext.dispose();
    }
    
    private void setInputManager() {
    	Gdx.input.setInputProcessor(this.gameContext.getInputManager());
		Gdx.input.setCursorCatched(true);
    }
    
    public void setResolution(int width, int height) {
		Gdx.graphics.setWindowedMode(width, height);
		Gdx.graphics.setResizable(false);
	}
}
