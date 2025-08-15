package com.haloofwar.ui.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.ui.hud.components.HealthBar;
import com.haloofwar.ui.hud.components.InventoryRenderer;
import com.haloofwar.ui.hud.components.PlayerInfoRenderer;

public class HUD {
	
	// Dependencias
    private final GameStaticCamera camera;
    private final SpriteBatch batch;
    
    // Componentes
    private final HealthBar health;
    private final PlayerInfoRenderer info;
    private final InventoryRenderer inventory;
    
    public HUD(
    	GameStaticCamera camera,
    	SpriteBatch batch,
		HealthBar health,
		PlayerInfoRenderer info,
		InventoryRenderer inventory
    ) {
    	this.camera = camera;
    	this.batch = batch;
    	this.health = health;
    	this.info = info;
    	this.inventory = inventory;
    }
    
    public void render() {
        this.camera.update();
        this.health.render();

        this.batch.setProjectionMatrix(this.camera.getOrthographic().combined);
        this.batch.begin();
        this.info.render();
        this.inventory.render();
        this.batch.end();
    }
    
    public GameStaticCamera getCamera() {
		return this.camera;
	}
    
    public void resize(int width, int height) {
        this.camera.resize(width, height);
    }

}