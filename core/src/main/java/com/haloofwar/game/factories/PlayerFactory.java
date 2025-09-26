package com.haloofwar.game.factories;

import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enums.PlayerType;
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

    public PlayerFactory(final GameContext CONTEXT) {
        this.TEXTURE = CONTEXT.getTEXTURE();
        this.GAMEPLAY_BUS = CONTEXT.getGAMEPLAY().getBus();
        this.WORLD_CAMERA = CONTEXT.getWORLD_CAMERA();
    }

    public Entity create(final PlayerType TYPE, final float X, final float Y) {   
    	final Entity ENTITY = new Entity();
    	ENTITY.addComponent(ComponentPresets.defaultTransform(X, Y));
    	ENTITY.addComponent(ComponentPresets.defaultObstacle());
		ENTITY.addComponent(ComponentPresets.defaultAnimation(TYPE, this.TEXTURE));
		ENTITY.addComponent(ComponentPresets.defaultHealth());
		ENTITY.addComponent(ComponentPresets.defaultCollision(X, Y));
		ENTITY.addComponent(ComponentPresets.playerMovement(this.GAMEPLAY_BUS));
		ENTITY.addComponent(ComponentPresets.defaultCrosshair(TYPE, this.TEXTURE, this.WORLD_CAMERA));
		ENTITY.addComponent(ComponentPresets.defaultName(TYPE));
		ENTITY.addComponent(ComponentPresets.defaultInventory());
		ENTITY.addComponent(ComponentPresets.defaultEquipment(TYPE.getWeapon()));
		ENTITY.addComponent(ComponentPresets.defaultPlayer(TYPE));
		ENTITY.addComponent(ComponentPresets.defaultVisibility());
		ENTITY.addComponent(ComponentPresets.defaultEquipment(TYPE.getWeapon()));
    
		return ENTITY;
    }
    
    public Entity create(final PlayerType TYPE, final float X, final float Y,
        InventoryData inventoryData, EquipmentData equipmentData) {   
    	final Entity ENTITY = new Entity();

		ENTITY.addComponent(ComponentPresets.defaultTransform(X, Y));
		ENTITY.addComponent(ComponentPresets.defaultObstacle());
		ENTITY.addComponent(ComponentPresets.defaultAnimation(TYPE, this.TEXTURE));
		ENTITY.addComponent(ComponentPresets.defaultHealth());
		ENTITY.addComponent(ComponentPresets.defaultCollision(X, Y));
		ENTITY.addComponent(ComponentPresets.playerMovement(this.GAMEPLAY_BUS));
		ENTITY.addComponent(ComponentPresets.defaultCrosshair(TYPE, this.TEXTURE, this.WORLD_CAMERA));
		ENTITY.addComponent(ComponentPresets.defaultName(TYPE));
		ENTITY.addComponent(ComponentPresets.defaultPlayer(TYPE));
		ENTITY.addComponent(ComponentPresets.defaultVisibility());
		ENTITY.addComponent(ComponentPresets.inventoryFromData(inventoryData, this.TEXTURE));
		ENTITY.addComponent(ComponentPresets.equipmentFromData(equipmentData));
		return ENTITY;
	}
    
    public Entity create(PlayerType type) {
    	return this.create(type, 0, 0);
    }
    
    public Entity create(PlayerType type, InventoryData inventoryData, EquipmentData equipmentData) {
    	return this.create(type, 0, 0, inventoryData, equipmentData);
    }
}
