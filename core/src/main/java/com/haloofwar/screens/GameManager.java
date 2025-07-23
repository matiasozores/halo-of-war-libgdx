package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.entities.characters.Kratos;
import com.haloofwar.entities.characters.MasterChief;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.game.GameScene;
import com.haloofwar.game.areas.Tutorial;
import com.haloofwar.utilities.GameContext;

public class GameManager implements Screen {
	private GameContext gameContext;
	
    private Player player;
    private GameScene currentScene;
  
    private boolean paused = false;
    private boolean loading = false;
    
    public GameManager(GameContext gameContext) {
		this.gameContext = gameContext;
	}
    
    @Override
    public void show() {
        int opc = 2;
        switch (opc) {
            case 1:
                this.player = new Kratos();
                break;
            case 2:
                this.player = new MasterChief();
                break;
            default:
                this.player = new Kratos();
                break;
        }

        this.currentScene = new Tutorial(this.gameContext, this.player); 
        this.currentScene.show();
    }

    @Override
    public void render(float delta) {
        this.update();
        this.currentScene.render(delta);
    }

    public void update() {
        this.currentScene.update();
    }

    public void setScene(GameScene newScene) {
        if (this.currentScene != null) {
            this.currentScene.dispose();
        }
        this.currentScene = newScene;
        this.currentScene.show();
    }

    @Override
    public void resize(int width, int height) {
        this.currentScene.resize(width, height);
    }

    @Override
    public void pause() {
        this.currentScene.pause();
    }

    @Override
    public void resume() {
        this.currentScene.resume();
    }

    @Override
    public void hide() {
        this.currentScene.hide();
    }

    @Override
    public void dispose() {
        if (this.currentScene != null) {
            this.currentScene.dispose();
        }
    }
}
