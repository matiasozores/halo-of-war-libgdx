package com.haloofwar.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.haloofwar.enumerators.EntityType;
import com.haloofwar.input.InputManager;
import com.haloofwar.utilities.Resources;

public class AnimationComponent {

    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> idleAnimation;
    private float stateTime = 0f;

    private SpriteBatch batch = Resources.getBatch();
    private TextureRegion currentFrame;

    private Texture spritesheet;

    public AnimationComponent(EntityType sprite) {
        this.spritesheet = new Texture(Gdx.files.internal("sprites/" + sprite.getFolder() + ".png"));

        this.idleAnimation = loadAnimationFromSheet(0, 4, 32, 32, 0.5f); 
        this.walkAnimation = loadAnimationFromSheet(1, 4, 32, 32, 0.5f);  
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

    public void update(InputManager inputManager) {
        this.stateTime += Gdx.graphics.getDeltaTime();

        if (inputManager.isMoving()) {
            this.currentFrame = this.walkAnimation.getKeyFrame(this.stateTime);
        } else {
            this.currentFrame = this.idleAnimation.getKeyFrame(this.stateTime);
        }
    }

    public void render(float x, float y, float width, float height, OrthographicCamera camera) {
        if (this.currentFrame != null) {
        	this.batch.setProjectionMatrix(camera.combined);
            this.batch.begin();
            this.batch.draw(this.currentFrame, x, y, width, height);
            this.batch.end();
        }
    }

    public void dispose() {
        if (spritesheet != null) {
            spritesheet.dispose();
        }
    }
}
