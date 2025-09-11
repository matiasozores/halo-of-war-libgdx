package com.haloofwar.game.cutscenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.events.EventBus;
import com.haloofwar.interfaces.Scene;

public class CutSceneData {
    public Texture[] images;
    public SpriteBatch batch;
    public EventBus bus;
    public Scene nextScene;

    public CutSceneData(Texture[] images, SpriteBatch batch, EventBus bus, Scene nextScene) {
        this.images = images;
        this.batch = batch;
        this.bus = bus;
        this.nextScene = nextScene;
    }
}
