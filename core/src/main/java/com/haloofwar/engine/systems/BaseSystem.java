package com.haloofwar.engine.systems;

import java.util.ArrayList;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.interfaces.Disposable;
import com.haloofwar.engine.interfaces.Registrable;

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
    public void dispose() {
    	this.ENTITIES.clear();
    }
}
