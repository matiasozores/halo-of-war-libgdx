package com.haloofwar.game.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.AnimatedEntityDescriptor;
import com.haloofwar.interfaces.StateController;

public class AnimationComponent implements Component {
    private final AnimationSet animationSet;
    private final StateController stateController;
    private final Texture spritesheet;

    public AnimationComponent(AnimatedEntityDescriptor descriptor, TextureManager textureManager, boolean isObject, boolean isLastState) {
        this.spritesheet = textureManager.get(descriptor);
        this.animationSet = new AnimationSet(this.spritesheet, descriptor);
        if(!isObject) {
        	this.stateController = new AnimationStateController(this.animationSet);
        } else {
        	this.stateController = new ObjectAnimationStateController(this.animationSet, isLastState);
        }
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
    
    public StateController getStateController() {
		return this.stateController;
	}
}
