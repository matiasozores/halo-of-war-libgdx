package com.haloofwar.game.scenes;

import com.haloofwar.common.enums.MusicTrack;
import com.haloofwar.game.world.World;
import com.haloofwar.interfaces.Scene;
import com.haloofwar.interfaces.SceneDescriptor;
import com.haloofwar.ui.hud.HUD;

public abstract class GameScene implements Scene {
    protected final World WORLD;
    protected final HUD HUD;
    private final SceneDescriptor DESCRIPTOR;
	
    public GameScene(final SceneDescriptor DESCRIPTOR, final World WORLD, final HUD HUD) {
        this.WORLD = WORLD;
        this.HUD = HUD;
        this.DESCRIPTOR = DESCRIPTOR;
	}

	@Override
	public void render(float delta) {
		this.WORLD.render();
		this.HUD.render(delta);
	}
	
	@Override
	public void update(float delta) {
		this.WORLD.update(delta);
	}
	
	@Override
	public void resize(int width, int height) {
		this.WORLD.getCamera().resize(width, height);
		this.HUD.resize(width, height);
	}
	
	@Override
	public void reconfigureCamera() {
		this.WORLD.reconfigureCamera();
	}
	
	@Override
	public void dispose() {
		this.HUD.dispose();
	}
	@Override
	public void reset() {
		this.HUD.reset();
	}
	
	@Override
	public World getWorld() {
		return this.WORLD;
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
	public MusicTrack getMusic() {
		return this.DESCRIPTOR.getMusic();
	}

}
