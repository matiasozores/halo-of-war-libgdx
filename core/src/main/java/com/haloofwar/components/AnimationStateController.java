package com.haloofwar.components;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.haloofwar.enumerators.SpriteState;

public class AnimationStateController {
    private final AnimationSet animationSet;
    private Animation<TextureRegion> currentAnimation;
    private float stateTime = 0f;
    private boolean facingLeft = false;

    public AnimationStateController(AnimationSet animationSet) {
        this.animationSet = animationSet;
        this.currentAnimation = animationSet.getAnimation(SpriteState.IDLE);
    }

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
        	// si no se puede mover entonces le ponemos que esta quieto xd
        	this.currentAnimation = this.animationSet.getAnimation(SpriteState.IDLE); 
        }


    }

    public TextureRegion getCurrentFrame() {
        return this.currentAnimation.getKeyFrame(stateTime);
    }

    public boolean isFacingLeft() {
        return this.facingLeft;
    }
}
