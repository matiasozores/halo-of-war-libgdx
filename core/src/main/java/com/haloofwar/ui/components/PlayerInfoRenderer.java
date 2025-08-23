package com.haloofwar.ui.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.components.HealthComponent;
import com.haloofwar.components.NameComponent;

public class PlayerInfoRenderer {
	
	// Dependencias
    private final SpriteBatch batch;
    private final BitmapFont smallFont;
    private final BitmapFont titleFont;
    private final Texture portrait;
    private final Texture weapon;
    
    // Componentes
    private HealthComponent health;
    private NameComponent nameComponent;
    
    private final float x = 20;
    private final float y = 640;
    
    public PlayerInfoRenderer(
		SpriteBatch batch,
		BitmapFont smallFont,
		BitmapFont titleFont,
		Texture portrait,
		Texture weapon,
		NameComponent nameComponent,
		HealthComponent health
	) {
        this.batch = batch;
        this.smallFont = smallFont;
        this.titleFont = titleFont;
        this.portrait = portrait;
        this.weapon = weapon;
        
        this.nameComponent = nameComponent;
        this.health = health;
    }
    
    public void render() {
        final String HP_TEXT = (int) this.health.getCurrentHealth() + "/" + this.health.getMaxHealth();
        this.smallFont.draw(this.batch, HP_TEXT, x + 80 + 100, y + 20);

        this.titleFont.draw(this.batch, this.nameComponent.name, x + 80, y + 70);
        
        this.batch.draw(this.weapon, 20, 575, 80, 65);
        

        if (this.portrait != null) {
            this.batch.draw(this.portrait, this.x, this.y, 66, 70);
        }
    }
    
    public void dispose() {
        if (portrait != null) {
        	portrait.dispose();
        }
        
        if (weapon != null) {
        	weapon.dispose();
        }
    }
}
