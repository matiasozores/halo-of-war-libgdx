package com.haloofwar.game;

import com.badlogic.gdx.Screen;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.MusicTrack;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.ui.HUD;

public abstract class GameScene implements Screen {
    private HUD hud;
	private World world;
	
    public GameScene(GameContext context, SceneType scene, Player player) {
    	this.world = new World(scene, player, context);
		this.hud = new HUD(context, player);
		context.getMusicManager().playMusic(MusicTrack.COSTA_PERDIDA);
	}

	@Override
	public void render(float delta) {
		this.world.render();
		this.hud.render();
	}
	
	public void update(float delta) {
		this.world.update(delta);
		this.hud.update();
	}
	
	@Override
	public void resize(int width, int height) {
		this.world.getCamera().resize(width, height);
		this.hud.getCamera().resize(width, height);
	}
	
    
    @Override
    public void show() {}
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
	@Override
	public void dispose() {}
}
