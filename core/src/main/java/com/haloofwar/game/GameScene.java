package com.haloofwar.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.audio.MusicManager;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.collision.CollisionManager;
import com.haloofwar.dependences.BulletManager;
import com.haloofwar.dependences.FontManager;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.MusicTrack;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.ui.HUD;

public abstract class GameScene implements Screen {
    private HUD hud;
	private World world;
	
    public GameScene(GameContext gameContext, SceneType scene, Player player) {
		final SpriteBatch BATCH = gameContext.getBatch();
		final GameWorldCamera CAMERA = gameContext.getCameraGame();
		final FontManager FONT = gameContext.getFontManager();
    	final MusicManager MUSIC = gameContext.getMusicManager();
    	final BulletManager BULLETS = gameContext.getBulletManager();
    	final TextureManager TEXTURE = gameContext.getTextureManager();
		final CollisionManager COLLISION = gameContext.getCollisionManager();
    	
    	
    	this.world = new World(scene, player, CAMERA, BATCH, BULLETS, TEXTURE, COLLISION);
		this.hud = new HUD(BATCH, FONT, player);
		MUSIC.playMusic(MusicTrack.COSTA_PERDIDA);
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
