package com.haloofwar.interfaces;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface StateController {
	public void update(float delta, float dirX, float dirY, boolean canMove);
    public TextureRegion getCurrentFrame();
    public boolean isFacingLeft();
    public void changeState();
}
