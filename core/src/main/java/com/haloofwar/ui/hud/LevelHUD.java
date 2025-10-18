package com.haloofwar.ui.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.ui.HUD;
import com.haloofwar.ui.components.HUDComponent;

public class LevelHUD extends HUD {
    public LevelHUD(
		final HUDComponent[] components,
        final GameStaticCamera camera,
        final SpriteBatch batch,
        final EventBus bus
	) {
    	super(components, camera, batch, bus);
    }

	@Override
	public void update(float delta) {
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
