package com.haloofwar.game.world;

import com.haloofwar.game.managers.LevelController;
import com.haloofwar.game.scenes.GameScene;
import com.haloofwar.interfaces.SceneDescriptor;
import com.haloofwar.ui.HUD;

public class Level extends GameScene {
    private final LevelController CONTROLLER;

    public Level(final SceneDescriptor DESCRIPTOR, final World WORLD, final HUD HUD, final LevelController CONTROLLER) {
        super(DESCRIPTOR, WORLD, HUD);
        this.CONTROLLER = CONTROLLER;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.CONTROLLER.update(delta);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        this.CONTROLLER.render(delta); 
    }

	@Override
	public boolean isLevel() {
		return true;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		this.CONTROLLER.dispose();
	}
	
	@Override
	public void reset() {
		super.reset();
		this.CONTROLLER.reset();
	}
}
