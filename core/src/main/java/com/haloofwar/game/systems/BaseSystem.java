package com.haloofwar.game.systems;

import java.util.ArrayList;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.interfaces.Disposable;
import com.haloofwar.engine.interfaces.Registrable;
import com.haloofwar.game.components.TransformComponent;

/*
 * Aclaracion: No todos los sitemas es necesario que extiendan de esta pero
 * es una base para aquellos que necesiten manejar entidades con componentes 
 * en especifico
 * 
 * */

public abstract class BaseSystem implements Registrable, Disposable {

    protected final ArrayList<Entity> ENTITIES = new ArrayList<>();

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
    public void unregisterByIdentifier(final int identifier) {
    	boolean found = false;
    	int i = 0;
    	
    	while(i < this.ENTITIES.size() && !found) {
    		if(this.ENTITIES.get(i).hasComponent(TransformComponent.class)) {
    			TransformComponent tc = this.ENTITIES.get(i).getComponent(TransformComponent.class);
    			if(tc.identifier == identifier) {
    				found = true;
    				this.ENTITIES.remove(i);
    			} else {
    				i++;
    			}
    		} else {
    			i++;
    		}
    	}
    }
    
    @Override
    public void dispose() {
    	this.ENTITIES.clear();
    }
}
