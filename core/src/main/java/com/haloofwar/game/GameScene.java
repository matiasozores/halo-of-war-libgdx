package com.haloofwar.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.ui.HUD;
import com.haloofwar.utilities.GameContext;

public abstract class GameScene implements Screen {
    private HUD hud;
	private World world;
	private SpriteBatch batch;
	
    public GameScene(GameContext gameContext, SceneType scene, Player player) {
		this.world = new World(gameContext, scene, player);
		this.hud = new HUD(gameContext, this.world.getPlayer());
		this.batch = gameContext.getBatch();
	}

	@Override
	public void render(float delta) {
		this.world.render(this.batch);
		this.hud.render(this.batch);
	}
	
	public void update() {
		this.world.update();
		this.hud.update();
	}
	
	@Override
	public void show() {
		
	}
	
	@Override
	public void resize(int width, int height) {
	
	}
	@Override
	public void pause() {

	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
