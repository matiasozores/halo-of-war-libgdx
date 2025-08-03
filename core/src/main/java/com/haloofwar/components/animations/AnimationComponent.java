package com.haloofwar.components.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.interfaces.EntityDescriptor;

public class AnimationComponent {
    private final AnimationSet animationSet;
    private final AnimationStateController stateController;
    private final AnimationRenderer renderer;
    private final Texture spritesheet;

    public AnimationComponent(EntityDescriptor descriptor, TextureManager textureManager) {
        this.spritesheet = textureManager.get(descriptor);
        this.animationSet = new AnimationSet(this.spritesheet, descriptor);
        this.stateController = new AnimationStateController(this.animationSet);
        this.renderer = new AnimationRenderer();
    }

    public void update(float delta, float dirX, float dirY) {
        this.stateController.update(delta, dirX, dirY);
    }

    public void render(float x, float y, float width, float height, SpriteBatch batch) {
        TextureRegion frame = this.stateController.getCurrentFrame();
        boolean facingLeft = this.stateController.isFacingLeft();
        this.renderer.render(frame, facingLeft, x, y, width, height, batch);
    }

    public void dispose() {

    }
}
