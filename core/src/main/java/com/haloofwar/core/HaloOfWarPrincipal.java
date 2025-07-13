package com.haloofwar.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.input.InputManager;
import com.haloofwar.screens.MainMenuScreen;
import com.haloofwar.utilities.Resources;

public class HaloOfWarPrincipal extends Game {
	private SpriteBatch batch;
    private InputManager inputManager;

    @Override
    public void create() {
    	Resources.setGame(this);
    	this.batch = Resources.getBatch();
    	this.inputManager = Resources.getInputManager();
    	Gdx.input.setInputProcessor(this.inputManager);
        this.setScreen(new MainMenuScreen());
        System.out.println("" + this.getClass().getSimpleName() + " created successfully.");
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
    	this.batch.dispose(); //
    }
}
