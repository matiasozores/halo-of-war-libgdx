package com.haloofwar.game.factories;

import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.cameras.GameWorldCamera;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.game.config.ComponentPresets;
import com.haloofwar.game.data.EquipmentData;
import com.haloofwar.game.data.InventoryData;

public final class PlayerFactory {

    private final TextureManager TEXTURE;
    private final EventBus GAMEPLAY_BUS;
    private final GameWorldCamera WORLD_CAMERA;
    private boolean alreadyCreated = false;
    
    public PlayerFactory(final GameContext CONTEXT) {
        this.TEXTURE = CONTEXT.getTEXTURE();
        this.GAMEPLAY_BUS = CONTEXT.getGameplay().getBus();
        this.WORLD_CAMERA = CONTEXT.getWorldCamera();
    }

    public Entity create(final int identifier, final PlayerType TYPE, final float X, final float Y) {   
    	final Entity ENTITY = new Entity();
    	ENTITY.addComponent(ComponentPresets.defaultTransform(identifier, X, Y));
		ENTITY.addComponent(ComponentPresets.defaultAnimation(TYPE, this.TEXTURE));
		ENTITY.addComponent(ComponentPresets.defaultHealth());
		ENTITY.addComponent(ComponentPresets.defaultCrosshair(TYPE, this.TEXTURE, this.WORLD_CAMERA));
		ENTITY.addComponent(ComponentPresets.defaultName(TYPE));
		ENTITY.addComponent(ComponentPresets.defaultInventory());
		ENTITY.addComponent(ComponentPresets.defaultEquipment(TYPE.getWeapon()));
		ENTITY.addComponent(ComponentPresets.defaultPlayer(TYPE));
		ENTITY.addComponent(ComponentPresets.defaultVisibility());
		ENTITY.addComponent(ComponentPresets.defaultEquipment(TYPE.getWeapon()));
    
		if(!this.alreadyCreated) {
			ENTITY.addComponent(ComponentPresets.remotePlayerMovement(identifier, this.GAMEPLAY_BUS));
			this.alreadyCreated = true;
		}  else {
			ENTITY.addComponent(ComponentPresets.remoteDefaultPlayerMovement());
		}

		return ENTITY;
    }
    
    public Entity create(final int identifier, final PlayerType TYPE, final float X, final float Y,
        InventoryData inventoryData, EquipmentData equipmentData) {   
    	
    	int count;
    	if(TYPE.equals(PlayerType.KRATOS)) {
    		count = -1;
    	} else if(TYPE.equals(PlayerType.MASTER_CHIEF)) {
    		count = -2;
    	} else {
    		count = -3;
    	}
    	
    	final Entity ENTITY = new Entity();

		ENTITY.addComponent(ComponentPresets.defaultTransform(identifier, X, Y));
		ENTITY.addComponent(ComponentPresets.defaultAnimation(TYPE, this.TEXTURE));
		ENTITY.addComponent(ComponentPresets.defaultHealth());
		ENTITY.addComponent(ComponentPresets.defaultCrosshair(TYPE, this.TEXTURE, this.WORLD_CAMERA));
		ENTITY.addComponent(ComponentPresets.defaultName(TYPE));
		ENTITY.addComponent(ComponentPresets.defaultPlayer(TYPE));
		ENTITY.addComponent(ComponentPresets.defaultVisibility());
		//ENTITY.addComponent(ComponentPresets.inventoryFromData(inventoryData, this.TEXTURE));
		//ENTITY.addComponent(ComponentPresets.equipmentFromData(equipmentData));
		
		if(!this.alreadyCreated) {
			ENTITY.addComponent(ComponentPresets.remotePlayerMovement(count, this.GAMEPLAY_BUS));
			this.alreadyCreated = true;
		} else {
			ENTITY.addComponent(ComponentPresets.remoteDefaultPlayerMovement());
		}
		
		return ENTITY;
	}
    
    public Entity create(final int identifier, PlayerType type) {
    	return this.create(identifier, type, 0, 0);
    }
    
    public Entity create(final int identifier, PlayerType type, InventoryData inventoryData, EquipmentData equipmentData) {
    	return this.create(identifier, type, 0, 0, inventoryData, equipmentData);
    }
}
