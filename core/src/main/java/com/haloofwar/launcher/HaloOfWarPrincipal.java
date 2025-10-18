package com.haloofwar.launcher;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.engine.utils.GraphicsUtils;
import com.haloofwar.network.ServerGameController;
import com.haloofwar.ui.menus.ServerScreen;

public class HaloOfWarPrincipal extends Game {
    private GameContext context;
    private ServerGameController controller;
    
    @Override
    public void create() {
    	
        this.initializeGame();
        this.controller = new ServerGameController(this.context.getGlobalBus(), this.context.getGAMEPLAY().getBus());
        this.controller.start();
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
        if (getScreen() != null) {
            getScreen().dispose();
        }

        super.dispose();
        if(this.context != null) {
            this.context.dispose();
        }

        if(this.controller != null){
            this.controller.dispose();
        }
    }

    private void initializeGame() {
        this.context = new GameContext(this);
        this.setScreen(new ServerScreen(this.context));
    }

    public void setResolution(int width, int height) {
        Gdx.graphics.setWindowedMode(width, height);
        Gdx.graphics.setResizable(false);
    }

    public void setFullscreen() {
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
    }

}
