package com.haloofwar.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.haloofwar.interfaces.EntityDescriptor;
import com.haloofwar.dependences.TextureManager;

public class AnimationComponent {
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> idleAnimation;
    private float stateTime = 0f;
    private TextureRegion currentFrame;
    private Texture spritesheet;
    private boolean facingLeft = false;

    public AnimationComponent(EntityDescriptor sprite, TextureManager textureManager) {
        this.spritesheet = textureManager.get(sprite);
        this.idleAnimation = loadAnimationFromSheet(0, sprite.getIdleLength(), 32, 32, 0.3f); 
        this.walkAnimation = loadAnimationFromSheet(1, sprite.getWalkLength(), 32, 32, 0.1f); 
    }

    private Animation<TextureRegion> loadAnimationFromSheet(int row, int frameCount, int frameWidth,
                                                            int frameHeight, float frameDuration) {
        TextureRegion[][] tmp = TextureRegion.split(this.spritesheet, frameWidth, frameHeight);

        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < frameCount; i++) {
            frames.add(tmp[row][i]);
        }

        return new Animation<>(frameDuration, frames, PlayMode.LOOP);
    }

    public void update(float delta, float dirX, float dirY) {
        this.stateTime += delta;

        boolean moving = dirX != 0 || dirY != 0;

        if (dirX < 0) {
            this.facingLeft = true;
        } else if (dirX > 0) {
            this.facingLeft = false;
        }

        if (moving) {
            this.currentFrame = this.walkAnimation.getKeyFrame(this.stateTime);
        } else {
            this.currentFrame = this.idleAnimation.getKeyFrame(this.stateTime);
        }
    }

    public void render(float x, float y, float width, float height, SpriteBatch batch) {
        if (this.currentFrame != null) {
            if (this.facingLeft) {
                batch.draw(this.currentFrame, x + width, y, -width, height);
            } else {
                batch.draw(this.currentFrame, x, y, width, height);
            }
        }
    }

    public void dispose() {
        if (this.spritesheet != null) {
            this.spritesheet.dispose();
        }
    }
}
