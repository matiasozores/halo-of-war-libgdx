package com.haloofwar.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.MainMenuScreen;
import com.haloofwar.utilities.GraphicsUtils;

public class HaloOfWarPrincipal extends Game {
    private GameContext context;
    
    @Override
    public void create() {
    	this.initializeGame();
    }

    @Override
    public void render() {
    	GraphicsUtils.cleanWindow();
    	super.render();
    }
    
    @Override
    public void resize(int width, int height) {
    	super.resize(width, height);
    }

    @Override
    public void dispose() {
    	if(this.context != null) {
    		this.context.dispose();
		}
    }
    
    private void initializeGame() {
        this.context = new GameContext(this);
        this.setInputManager();
        this.setScreen(new MainMenuScreen(this.context));
    }
    
    private void setInputManager() {
    	Gdx.input.setInputProcessor(this.context.getInput());
		Gdx.input.setCursorCatched(true);
    }
    
    public void setResolution(int width, int height) {
		Gdx.graphics.setWindowedMode(width, height);
		Gdx.graphics.setResizable(false);
	}
    
    public void setFullscreen() {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    }

}
