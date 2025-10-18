package com.haloofwar.game.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PeacefulEvent;
import com.haloofwar.engine.interfaces.Renderable;
import com.haloofwar.game.components.CrosshairComponent;
import com.haloofwar.interfaces.Updatable;

public class CrosshairSystem extends EventSystem implements Updatable, Renderable {
	
    private final SpriteBatch batch;
    private boolean peaceful = false;
    
    public CrosshairSystem(EventBus bus, SpriteBatch batch) {
    	super(bus);
        this.batch = batch;
        this.listenerManager.add(bus, PeacefulEvent.class, this::onPeace);
    }

    private void onPeace(PeacefulEvent event) {
    	this.peaceful = event.isPeaceful;
    }
    
    @Override
    public void register(Entity e) {
        if (e.hasComponent(CrosshairComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
    	if(this.peaceful) {
    		return;
    	}
    	
        for (Entity entity : this.ENTITIES) {
            CrosshairComponent crosshair = entity.getComponent(CrosshairComponent.class);
            if (crosshair == null) {
            	continue;
            }

            Vector3 mouseWorldPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            crosshair.camera.getOrthographic().unproject(mouseWorldPos);

            crosshair.mouseX = (int) mouseWorldPos.x;
            crosshair.mouseY = (int) mouseWorldPos.y;
        }
    }

    @Override
    public void render() {
        for (Entity entity : this.ENTITIES) {
            CrosshairComponent crosshair = entity.getComponent(CrosshairComponent.class);
            if (crosshair == null) continue;

            this.batch.draw(
                crosshair.texture,
                crosshair.mouseX,
                crosshair.mouseY,
                crosshair.width,
                crosshair.height
            );
        }
    }
}
