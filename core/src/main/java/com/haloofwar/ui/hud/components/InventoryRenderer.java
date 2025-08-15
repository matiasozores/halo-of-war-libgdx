package com.haloofwar.ui.hud.components;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.collision.PickupComponent;
import com.haloofwar.ecs.components.gameplay.InventoryComponent;
import com.haloofwar.ecs.components.render.RenderComponent;

public class InventoryRenderer {
    
	// Dependencias
	private final SpriteBatch batch;
    private final BitmapFont smallFont;
    
    // Componentes
    private InventoryComponent inventory;
    
    private final float x = 100;
    private final float y = 620;
    
    public InventoryRenderer(
		SpriteBatch batch,
		BitmapFont smallFont,
		InventoryComponent inventory
	) {
        this.batch = batch;
        this.smallFont = smallFont;
        
        this.inventory = inventory;
    }

    public void render() {
		if (this.inventory.getObjects() != null) {
            ArrayList<Entity> objects = this.inventory.getObjects();
            float currentX = this.x;

            for (Entity object : objects) {
            	PickupComponent pickup;
            	RenderComponent render;
            	
            	if(object.hasComponent(PickupComponent.class) && object.hasComponent(RenderComponent.class)) {
            		pickup = object.getComponent(PickupComponent.class);
            		render = object.getComponent(RenderComponent.class);
        	
	    	        final Texture texture = render.texture;
	                int stock = pickup.getStock();

	                this.batch.draw(texture, currentX, this.y - 50, 32, 32); 

	                if (stock > 1) {
	                   this.smallFont.draw(this.batch, "x" + stock, currentX + 15, this.y - 40);
	                }

                	currentX += 40;	
            	}
            }
            
            this.smallFont.draw(this.batch, "Items en inventario:", this.x, this.y + 10);
        }
    }
}