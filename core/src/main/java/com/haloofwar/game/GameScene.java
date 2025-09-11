package com.haloofwar.game;

import com.haloofwar.interfaces.Scene;
import com.haloofwar.ui.HUD;

public class GameScene implements Scene {
    protected final World world;
    protected final HUD hud;
	
    public GameScene(World world, HUD hud) {
        this.world = world;
        this.hud = hud;
	}

	@Override
	public void render(float delta) {
		this.world.render();
		this.hud.render();
	}
	
	@Override
	public void update(float delta) {
		this.world.update(delta);
	}
	
	@Override
	public void resize(int width, int height) {
		this.world.getCamera().resize(width, height);
		this.hud.resize(width, height);
	}
	
	@Override
	public void reconfigureCamera() {
		this.world.reconfigureCamera();
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
