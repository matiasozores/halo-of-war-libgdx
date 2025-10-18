package com.haloofwar.engine.cameras;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.components.TransformComponent;
import com.haloofwar.game.world.MapMetaData;
import com.haloofwar.utils.GameConfig;

public class GameWorldCamera extends GameCamera {
    private TransformComponent target;
    private MapMetaData map;
    private float zoom = 0.4f;
    
    public GameWorldCamera() {
        super(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT); 
    }
    
    public void configure(Entity player, MapMetaData map) {
        if(player.hasComponent(TransformComponent.class)) {
        	this.target = player.getComponent(TransformComponent.class);
        }
    	
        this.map = map;
    }

    @Override
    public void update() {
    	if(this.target != null && this.map != null) {
            this.camera.zoom = this.zoom;

            float halfW = this.camera.viewportWidth * this.camera.zoom / 2f;
            float halfH = this.camera.viewportHeight * this.camera.zoom / 2f;

            float camX = Math.max(halfW, Math.min(this.target.x, this.map.getMapPixelWidth() - halfW));
            float camY = Math.max(halfH, Math.min(this.target.y, this.map.getMapPixelHeight() - halfH));
            
            this.camera.position.set(camX, camY, 0);
            this.camera.update();
    	} else {
    		System.out.println("Para usar la camera de mundo, primero debes configurar el jugador y el mapa.");
    		return;
    	}
    }
    
    public void changeTarget(Entity newTarget) {
        if (newTarget != null && newTarget.hasComponent(TransformComponent.class)) {
            this.target = newTarget.getComponent(TransformComponent.class);
        } else {
            System.out.println("El nuevo target no tiene TransformComponent o es nulo.");
        }
    }

}
