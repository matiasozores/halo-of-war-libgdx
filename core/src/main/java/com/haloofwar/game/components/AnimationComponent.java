package com.haloofwar.game.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.components.Component;
import com.haloofwar.engine.entity.AnimatedEntityDescriptor;

public class AnimationComponent implements Component {
    private final AnimationSet animationSet;
    private final AnimationStateController stateController;
    private final Texture spritesheet;

    public AnimationComponent(AnimatedEntityDescriptor descriptor, TextureManager textureManager) {
        this.spritesheet = textureManager.get(descriptor);
        this.animationSet = new AnimationSet(this.spritesheet, descriptor);
        this.stateController = new AnimationStateController(this.animationSet);
    }

    public void update(float delta, float dirX, float dirY, boolean canMove) {
        this.stateController.update(delta, dirX, dirY, canMove);
    }
    
    public TextureRegion getCurrentFrame() {
        return this.stateController.getCurrentFrame();
    }

    public boolean isFacingLeft() {
        return this.stateController.isFacingLeft();
    }
}
