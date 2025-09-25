package com.haloofwar.game.systems;

import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.DropItemEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.interfaces.Registrable;
import com.haloofwar.game.components.EnemyComponent;

public class EnemySystem implements Registrable {

	private final EventBus bus;
	
    public EnemySystem(EventBus bus) {
        this.bus = bus;
    	this.bus.subscribe(RemoveEntityEvent.class, this::onRemoveEntity);
    }
	
    private void onRemoveEntity(RemoveEntityEvent event) {
    	if(event.entity.hasComponent(EnemyComponent.class)) {
    		TransformComponent transform = event.entity.getComponent(TransformComponent.class);
    		this.bus.publish(new DropItemEvent(transform.x, transform.y));
    	}
    }

    @Override
    public void register(Entity e) {
     
    }
    
	@Override
	public void unregister(Entity entity) {
		// TODO Auto-generated method stub
		
	}
}
