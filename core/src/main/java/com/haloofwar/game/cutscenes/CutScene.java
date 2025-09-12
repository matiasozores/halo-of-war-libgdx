package com.haloofwar.game.cutscenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.events.ChangeSceneEvent;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.GameStateEvent;
import com.haloofwar.events.NextEvent;
import com.haloofwar.interfaces.Scene;

public class CutScene implements Scene {
	
    private Texture[] images;
    private SpriteBatch batch;
    private EventBus bus;
    private Scene nextScene;
    private int currentIndex = 0; // para saber qué imagen mostrar
    private boolean finished = false;
    
    public CutScene(CutSceneData data) {
        this.images = data.images;
        this.batch = data.batch;
        this.bus = data.bus;
        this.nextScene = data.nextScene;

        this.bus.subscribe(NextEvent.class, this::onNext);
    }

    private void onNext(NextEvent event) {
        if (!event.isPressed() || finished) return;

        if (currentIndex >= images.length - 1) {
            finished = true; // marcamos que ya terminamos
            this.bus.publish(new GameStateEvent(GameState.PLAYING));
            this.bus.publish(new ChangeSceneEvent(this.nextScene));
        } else {
            currentIndex++;
        }
    }


    @Override
    public void update(float delta) {
    }

    @Override
    public void render(float delta) {
        if (this.currentIndex < this.images.length) {
        	this.batch.begin();
            this.batch.draw(this.images[this.currentIndex], 0, 0); 
            this.batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {
        this.currentIndex = 0;
        this.finished = false;
    }


    @Override
    public void hide() {
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}

    @Override
    public void reconfigureCamera() {}
}
