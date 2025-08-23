package com.haloofwar.components;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.haloofwar.enumerators.SpriteState;
import com.haloofwar.interfaces.AnimatedEntityDescriptor;

public class AnimationSet {
    private final Map<SpriteState, Animation<TextureRegion>> animations = new HashMap<>();

    public AnimationSet(Texture spritesheet, AnimatedEntityDescriptor descriptor) {
        this.animations.put(SpriteState.IDLE, loadAnimation(spritesheet, 0, descriptor.getIdleLength(), 32, 32, 0.3f));
        this.animations.put(SpriteState.WALK, loadAnimation(spritesheet, 1, descriptor.getWalkLength(), 32, 32, 0.1f));
    }

    private Animation<TextureRegion> loadAnimation(Texture sheet, int row, int frameCount, int frameWidth,
                                                  int frameHeight, float frameDuration) {
        TextureRegion[][] tmp = TextureRegion.split(sheet, frameWidth, frameHeight);
        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < frameCount; i++) {
            frames.add(tmp[row][i]);
        }
        return new Animation<>(frameDuration, frames, Animation.PlayMode.LOOP);
    }

    public Animation<TextureRegion> getAnimation(SpriteState key) {
        return this.animations.get(key);
    }
}

