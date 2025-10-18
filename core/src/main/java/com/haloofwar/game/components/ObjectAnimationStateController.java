package com.haloofwar.game.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.haloofwar.common.enumerators.SpriteState;
import com.haloofwar.interfaces.StateController;

public class ObjectAnimationStateController implements StateController {
    private final AnimationSet animationSet;
    private Animation<TextureRegion> currentAnimation;
    private float stateTime = 0f;
    private boolean secondState = false;
    
    
    public ObjectAnimationStateController(AnimationSet animationSet, boolean isLastState) {
        this.animationSet = animationSet;
        this.currentAnimation = animationSet.getAnimation(SpriteState.IDLE);
        this.secondState = isLastState;
    }

    public void update(float delta, float dirX, float dirY, boolean canMove) {
        this.stateTime += delta;

        if (!this.secondState) {
            this.currentAnimation = this.animationSet.getAnimation(SpriteState.WALK);
        } else {
            this.currentAnimation = this.animationSet.getAnimation(SpriteState.IDLE);
        } 
    }

    public TextureRegion getCurrentFrame() {
        return this.currentAnimation.getKeyFrame(stateTime);
    }
    
	@Override
	public boolean isFacingLeft() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void changeState() {
		this.secondState = true;
	}
}
