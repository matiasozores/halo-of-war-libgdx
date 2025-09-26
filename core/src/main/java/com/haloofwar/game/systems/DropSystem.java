package com.haloofwar.game.systems;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.common.enums.ObjectType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.DropItemEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.systems.EventSystem;
import com.haloofwar.engine.utils.RandomUtils;
import com.haloofwar.game.factories.ObjectFactory;

public class DropSystem extends EventSystem {

    private EventBus bus;
    private TextureManager manager;
    private static final int CHANCE = 90;
    
    public DropSystem(EventBus bus, TextureManager manager) {
        this.bus = bus;
        this.manager = manager;
        this.listenerManager.add(bus, DropItemEvent.class, this::onDropItem);
    }

    private void onDropItem(DropItemEvent event) {
        if (RandomUtils.checkChance(CHANCE)) {
            float x = event.x;
            float y = event.y;
            ObjectType randomItem = ObjectType.generate();
            Entity entity = ObjectFactory.createItem(new Rectangle(x, y, 16, 16), randomItem, this.manager);
            this.bus.publish(new NewEntityEvent(entity));
        }
    }
}
