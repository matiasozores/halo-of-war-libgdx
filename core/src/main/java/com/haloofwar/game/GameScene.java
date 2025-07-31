package com.haloofwar.game;

import com.badlogic.gdx.Screen;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.game.components.SceneBuilder;
import com.haloofwar.ui.HUD;

public abstract class GameScene implements Screen {
    protected final GameContext context;
    protected final SceneType sceneType;
    protected final Player player;

    protected final World world;
    protected final HUD hud;
	
    public GameScene(GameContext context, SceneType scene, Player player) {
        this.context = context;
        this.sceneType = scene;
        this.player = player;

        this.world = SceneBuilder.build(this.sceneType, context, player);
        this.hud = new HUD(context, player, player.getType());
        
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
