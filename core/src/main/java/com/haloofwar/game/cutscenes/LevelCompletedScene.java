package com.haloofwar.game.cutscenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.enums.GameState;
import com.haloofwar.common.enums.MusicTrack;
import com.haloofwar.common.enums.SceneType;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.events.ChangeSceneEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.interfaces.Scene;

public class LevelCompletedScene implements Scene {

    private SpriteBatch batch;
    private Texture levelCompletedTexture;
    private EventBus bus;
    private SceneType type;

    private boolean finished = false;
    private float timer = 0f;
    private final float DISPLAY_TIME = 10f; // segundos antes de pasar automáticamente

    private GameStaticCamera camera;
    
    public LevelCompletedScene(GameStaticCamera camera, SpriteBatch batch, Texture levelCompletedTexture, EventBus bus, SceneType type) {
        this.batch = batch;
        this.levelCompletedTexture = levelCompletedTexture;
        this.bus = bus;
        this.type = type;
        this.camera = camera;
    }

    private void finishScene() {
        finished = true;
        bus.publish(new GameStateEvent(GameState.PLAYING));
        bus.publish(new ChangeSceneEvent(type));
    }

    @Override
    public void update(float delta) {
        if (finished) return;

        timer += delta;
        if (timer >= DISPLAY_TIME) {
            finishScene();
        }
    }

    @Override
    public void render(float delta) {
    	batch.setProjectionMatrix(this.camera.getOrthographic().combined);
        batch.begin();
        batch.draw(this.levelCompletedTexture, 0, 0, this.camera.getViewportWidth(), this.camera.getViewportHeight());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {
        finished = false;
        timer = 0f;
    }

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {
    }

    @Override
    public void reconfigureCamera() {}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLevel() {
		return true;
	}

	@Override
	public MusicTrack getMusic() {
		return MusicTrack.VICTORY;
	}
	
	
}
