package com.haloofwar.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.haloofwar.dependences.InputManager;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.interfaces.EntityDescriptor;

public class AnimationComponent {

    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> idleAnimation;
    private float stateTime = 0f;
    private TextureRegion currentFrame;
    private Texture spritesheet;
    private boolean facingLeft = false;
    
    private InputManager inputManager;

    public AnimationComponent(EntityDescriptor sprite, InputManager inputManager, TextureManager textureManager) {
        this.spritesheet = textureManager.get(sprite);
        this.idleAnimation = loadAnimationFromSheet(0, sprite.getIdleLength(), 32, 32, 0.3f); 
        this.walkAnimation = loadAnimationFromSheet(1, sprite.getWalkLength(), 32, 32, 0.1f); 
        this.inputManager = inputManager;
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

    public void update(float delta) {
        this.stateTime += delta;

        boolean left = this.inputManager.isMoveLeft();
        boolean right = this.inputManager.isMoveRight();
        boolean verticalMovement = (this.inputManager.isMoveUp() || this.inputManager.isMoveDown()) ? true : false;
        if (left && right && !verticalMovement) {
            this.currentFrame = this.idleAnimation.getKeyFrame(this.stateTime);
        } 
        else if (left) {
            this.currentFrame = this.walkAnimation.getKeyFrame(this.stateTime);
            this.facingLeft = true;
        } 
        else if (right) {
            this.currentFrame = this.walkAnimation.getKeyFrame(this.stateTime);
            this.facingLeft = false;
        }
        else if (verticalMovement) {
            this.currentFrame = this.walkAnimation.getKeyFrame(this.stateTime);
        }
        else {
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
