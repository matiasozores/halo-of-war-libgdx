package com.haloofwar.game.factories;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.common.enums.ObjectType;
import com.haloofwar.common.enums.PowerUpType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.config.ComponentPresets;

public final class ObjectFactory {
    private static final float WIDTH = 16, HEIGHT = 16;

    public static Entity createItemFromName(final String NAME, final TextureManager TEXTURE) {
    	final ObjectType TYPE = ObjectType.getByName(NAME);
    	
    	if(TYPE == null) {
    		System.out.println("No se puede crear el objeto porque el nombre que se ingreso no coincide... | ObjectFactory");
    		return null;
    	}
    	
    	return createItem(new Rectangle(0,0,0,0), TYPE, TEXTURE);
    }
    
    public static Entity createItem(final Rectangle RECTANGLE, final ObjectType TYPE, final TextureManager TEXTURE) {
    	final Entity ENTITY = new Entity();
    	ENTITY.addComponent(ComponentPresets.defaultTransform(RECTANGLE.x, RECTANGLE.y, WIDTH, HEIGHT));
    	ENTITY.addComponent(ComponentPresets.defaultCollision(RECTANGLE.width, RECTANGLE.height));
    	ENTITY.addComponent(ComponentPresets.defaultName(TYPE));
    	ENTITY.addComponent(ComponentPresets.defaultRender(TYPE, TEXTURE));
    	ENTITY.addComponent(ComponentPresets.defaultPickup(TYPE));
    	
    	return ENTITY;
    }

    public static Entity createPowerUp(final Rectangle RECTANGLE, final PowerUpType TYPE, final TextureManager TEXTURE) {
    	final Entity ENTITY = new Entity();
    	ENTITY.addComponent(ComponentPresets.defaultTransform(RECTANGLE.x, RECTANGLE.y, WIDTH, HEIGHT));
    	ENTITY.addComponent(ComponentPresets.defaultCollision(RECTANGLE.width, RECTANGLE.height));
    	ENTITY.addComponent(ComponentPresets.defaultName(TYPE));
    	ENTITY.addComponent(ComponentPresets.defaultRender(TYPE, TEXTURE));
    	ENTITY.addComponent(ComponentPresets.defaultShield());
       	ENTITY.addComponent(ComponentPresets.defaultCollectible());
       	ENTITY.addComponent(ComponentPresets.defaultPowerUp(TYPE));
    	
    	return ENTITY;
    }
}