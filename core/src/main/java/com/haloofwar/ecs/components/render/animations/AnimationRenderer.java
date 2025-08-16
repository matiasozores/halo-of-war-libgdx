package com.haloofwar.ecs.components.render.animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationRenderer {
    public void render(TextureRegion frame, boolean facingLeft, float x, float y, float width, float height, SpriteBatch batch) {
        if (frame == null) {
        	return;
        }

        if (facingLeft) {
            batch.draw(frame, x + width, y, -width, height);
        } else {
            batch.draw(frame, x, y, width, height);
        }
    }
}
