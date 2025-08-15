package com.haloofwar.game;

import com.badlogic.gdx.Screen;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.ecs.Entity;
import com.haloofwar.enumerators.game.SceneType;
import com.haloofwar.factories.HUDFactory;
import com.haloofwar.game.components.SceneBuilder;
import com.haloofwar.ui.hud.HUD;

public abstract class GameScene implements Screen {
    protected final GameContext context;
    protected final Entity player;
    protected final World world;
    protected final HUD hud;
	
    public GameScene(GameContext context, SceneType scene, Entity player) {
        this.context = context;
        this.player = player;

        this.world = SceneBuilder.build(scene, context, player);
        HUDFactory hudFactory = new HUDFactory(context);
        this.hud = hudFactory.create();
        
        
        if(scene.getMusic() != null) {
        	this.context.getAudio().getMusic().play(scene.getMusic());
        }
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
