package com.haloofwar.game.cutscenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.enums.SoundType;
import com.haloofwar.engine.events.EventBus;

public class CutSceneData {
    public Texture[] images;
    public SoundType[] sounds;
    public SpriteBatch batch;
    public EventBus bus;

    public CutSceneData(Texture[] images, SoundType[] sounds, SpriteBatch batch, EventBus bus) {
        this.images = images;
        this.batch = batch;
        this.bus = bus;
        this.sounds = sounds;
    }
}
