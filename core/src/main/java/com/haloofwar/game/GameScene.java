package com.haloofwar.game;

import com.badlogic.gdx.Screen;
import com.haloofwar.components.Entity;
import com.haloofwar.ui.HUD;

public class GameScene implements Screen {
    protected final Entity player;
    protected final World world;
    protected final HUD hud;
	
    public GameScene(World world, HUD hud, Entity player) {
        this.player = player;
        this.world = world;
        this.hud = hud;
	}

	@Override
	public void render(float delta) {
		this.world.render();
		this.hud.render();
	}
	
	public void update(float delta) {
		this.world.update(delta);
	}
	
	@Override
	public void resize(int width, int height) {
		this.world.getCamera().resize(width, height);
		this.hud.resize(width, height);
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
