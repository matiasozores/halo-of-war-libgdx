package com.haloofwar.ui.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.NewPlayerEvent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.ui.components.HUDComponent;

public abstract class HUD {
    private final GameStaticCamera camera;
    protected final SpriteBatch batch;
    private final HUDComponent[] components;
    private final EventListenerManager listenerManager = new EventListenerManager();
    
    public HUD(
		final HUDComponent[] components,
        final GameStaticCamera camera,
        final SpriteBatch batch,
        final EventBus bus
	) {
    	if(components == null) {
    		System.out.println("Los componentes del HUD son nulos... | HUD");
    	}
    	
    	this.components = components;
    	this.camera = camera;
    	this.batch = batch;
    	this.listenerManager.add(bus, NewPlayerEvent.class, this::onNewPlayer);
    }

    protected void onNewPlayer(NewPlayerEvent event) {
    	if(event.player.hasComponent(PlayerComponent.class)) {
    		for (HUDComponent component : this.components) {
				component.refresh(event.player);
			}
    	}
    }

    public void render(float delta) {
        this.update(delta);
        this.camera.update();

        if (this.batch != null) {
            this.batch.setProjectionMatrix(this.camera.getOrthographic().combined);
            this.batch.begin();
            
            for (HUDComponent component : this.components) {
				component.render();
            }
            
            this.batch.end();
        }
    }

    public abstract void update(float delta);

    public GameStaticCamera getCamera() {
        return this.camera;
    }

    public void resize(int width, int height) {
        if (this.camera != null) {
        	this.camera.resize(width, height);
        }
    }

    public void dispose() {
    	this.listenerManager.clear();
    	
    	
		for (HUDComponent component : this.components) {
			component.dispose();
		}
    }

    public void reset() {

    }
}
