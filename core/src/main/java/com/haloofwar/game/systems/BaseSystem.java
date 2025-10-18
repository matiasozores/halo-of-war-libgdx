package com.haloofwar.game.systems;

import java.util.ArrayList;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.events.online.RemoveEntityEventOnline;
import com.haloofwar.engine.interfaces.Disposable;
import com.haloofwar.engine.interfaces.Registrable;
import com.haloofwar.game.components.Component;
import com.haloofwar.game.components.TransformComponent;

/*
 * Aclaracion: No todos los sitemas es necesario que extiendan de esta pero
 * es una base para aquellos que necesiten manejar entidades con componentes 
 * en especifico
 * 
 * */

public abstract class BaseSystem implements Registrable, Disposable {

    protected final ArrayList<Entity> ENTITIES = new ArrayList<>();
    protected EventBus bus;
    
    public BaseSystem(EventBus bus) {
    	this.bus = bus;
    }
    
    @Override
    public void register(Entity e) {
        if (!this.ENTITIES.contains(e)) {
            this.ENTITIES.add(e);
        }
    }

    @Override
    public void unregister(Entity e) {
        this.ENTITIES.remove(e);
    }
    
    @Override
    public void unregisterByComponent(Class<? extends Component> component) {
        for(int i = this.ENTITIES.size() - 1; i >= 0; i--) {
        	if(this.ENTITIES.get(i).hasComponent(component)) {
        		
        		Entity entity = this.ENTITIES.remove(i);
        		this.bus.publish(new RemoveEntityEvent(entity));
        		
        		if(entity.hasComponent(TransformComponent.class)) {
        			TransformComponent tc = entity.getComponent(TransformComponent.class);
        			this.bus.publish(new RemoveEntityEventOnline(tc.identifier));
        		}
        	}
        }
    }
    
    @Override
    public void dispose() {
    	this.ENTITIES.clear();
    }
}
