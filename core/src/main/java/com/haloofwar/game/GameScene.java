package com.haloofwar.game;

import com.badlogic.gdx.Screen;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.players.Player;
import com.haloofwar.enumerators.game.SceneType;
import com.haloofwar.game.components.SceneBuilder;
import com.haloofwar.ui.hud.HUD;

public abstract class GameScene implements Screen {
    protected final GameContext context;
    protected final Player player;
    protected final World world;
    protected final HUD hud;
	
    public GameScene(GameContext context, SceneType scene, Player player) {
        this.context = context;
        this.player = player;

        this.world = SceneBuilder.build(scene, context, player);
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
