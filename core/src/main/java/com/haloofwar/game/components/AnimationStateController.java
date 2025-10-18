package com.haloofwar.game.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.haloofwar.common.enumerators.SpriteState;
import com.haloofwar.interfaces.StateController;

public class AnimationStateController implements StateController {
    private final AnimationSet animationSet;
    private Animation<TextureRegion> currentAnimation;
    private float stateTime = 0f;
    private boolean facingLeft = false;

    public AnimationStateController(AnimationSet animationSet) {
        this.animationSet = animationSet;
        this.currentAnimation = animationSet.getAnimation(SpriteState.IDLE);
    }

    @Override
    public void update(float delta, float dirX, float dirY, boolean canMove) {
        this.stateTime += delta;

        boolean moving = dirX != 0 || dirY != 0;

        if(canMove) {
            if (dirX < 0) {
            	this.facingLeft = true;
            }
            else if (dirX > 0) {
            	this.facingLeft = false;
            }

            if (moving) {
                this.currentAnimation = this.animationSet.getAnimation(SpriteState.WALK);
            } else {
                this.currentAnimation = this.animationSet.getAnimation(SpriteState.IDLE);
            } 
        } else {
        	this.currentAnimation = this.animationSet.getAnimation(SpriteState.IDLE); 
        }


    }

    public TextureRegion getCurrentFrame() {
        return this.currentAnimation.getKeyFrame(stateTime);
    }

    public boolean isFacingLeft() {
        return this.facingLeft;
    }

	@Override
	public void changeState() {
		
	}
}
