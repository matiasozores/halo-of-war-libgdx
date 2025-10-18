package com.haloofwar.game.systems;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.common.enumerators.ObjectType;
import com.haloofwar.common.enumerators.PowerUpType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.DropItemEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.events.online.SpawnItemEventOnline;
import com.haloofwar.engine.events.online.SpawnPowerUpEventOnline;
import com.haloofwar.engine.utils.RandomUtils;
import com.haloofwar.game.components.PowerUpComponent;
import com.haloofwar.game.components.StockComponent;
import com.haloofwar.game.components.TransformComponent;
import com.haloofwar.game.factories.ObjectFactory;

public class DropSystem extends EventSystem {

    private TextureManager manager;
    private static final int CHANCE = 90;
    private static final int ITEM_SPAWN = 10;
    
    public DropSystem(EventBus bus, TextureManager manager) {
    	super(bus);
        this.manager = manager;
        this.listenerManager.add(bus, DropItemEvent.class, this::onDropItem);
    }

    private void onDropItem(DropItemEvent event) {
    	final boolean SPAWN_PROBABILITY = RandomUtils.checkChance(CHANCE);
    	
    	if(SPAWN_PROBABILITY) {
    		final boolean SPAWN_ITEM = RandomUtils.checkChance(ITEM_SPAWN);
    		float x = event.x;
            float y = event.y;
    		
            if (SPAWN_ITEM) {
                ObjectType randomItem = ObjectType.generate();
                Entity entity = ObjectFactory.createItem(new Rectangle(x, y, 16, 16), randomItem, this.manager);
                StockComponent stock = entity.getComponent(StockComponent.class);
            	TransformComponent transform = entity.getComponent(TransformComponent.class);
            	this.bus.publish(new SpawnItemEventOnline(transform.identifier, stock.getType(), transform.x, transform.y));
                this.bus.publish(new NewEntityEvent(entity));
            } else {
            	PowerUpType randomPowerUp = PowerUpType.generate();
            	Entity entity = ObjectFactory.createPowerUp(new Rectangle(x, y, 16, 16), randomPowerUp, this.manager);
                PowerUpComponent powerUpComp = entity.getComponent(PowerUpComponent.class);
            	TransformComponent transform = entity.getComponent(TransformComponent.class);
            	this.bus.publish(new SpawnPowerUpEventOnline(transform.identifier, powerUpComp.type, transform.x, transform.y));
            	this.bus.publish(new NewEntityEvent(entity));
            }
    	}
    }
}
