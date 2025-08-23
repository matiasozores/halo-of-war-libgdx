package com.haloofwar.systems;

import java.util.ArrayList;

import com.haloofwar.components.Entity;
import com.haloofwar.interfaces.Disposable;
import com.haloofwar.interfaces.Registrable;

/*
 * Aclaracion: No todos los sitemas es necesario que extiendan de esta pero
 * es una base para aquellos que necesiten manejar entidades con componentes 
 * en especifico
 * 
 * */

public abstract class BaseSystem implements Registrable, Disposable {

    protected final ArrayList<Entity> entities = new ArrayList<>();

    @Override
    public void register(Entity e) {
        if (!this.entities.contains(e)) {
            this.entities.add(e);
        }
    }

    @Override
    public void unregister(Entity e) {
        this.entities.remove(e);
    }
    
    @Override
    public void dispose() {
    	this.entities.clear();
    }
}
