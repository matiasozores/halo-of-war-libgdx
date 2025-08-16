package com.haloofwar.ui.hud.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.ecs.components.gameplay.HealthComponent;

public class HealthBar {
	
	// Dependencias
    private final ShapeRenderer shape;
    private final GameStaticCamera camera;
    
    // Componentes
    HealthComponent health;
    
    private final float x = 100;
    private final float y = 640;
    private final float width = 200;
    private final float height = 25;

    
    public HealthBar(
		ShapeRenderer shape,
		GameStaticCamera camera,
		HealthComponent health
	) {
        this.shape = shape;
        this.camera = camera;
        this.health = health;   
    }
    
    public void render() {
        float HP_PERCENTAGE = Math.max(0, this.health.getCurrentHealth() / (float) this.health.getMaxHealth());
        
        
        this.shape.setProjectionMatrix(this.camera.getOrthographic().combined);
        this.shape.begin(ShapeRenderer.ShapeType.Filled);
        this.shape.setColor(Color.DARK_GRAY);
        this.shape.rect(this.x, this.y, this.width, this.height);
        this.shape.setColor(Color.GREEN);
        this.shape.rect(this.x, this.y, this.width * HP_PERCENTAGE, this.height);
        this.shape.end();
    }
}