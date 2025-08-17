package com.haloofwar.ecs.systems;

import java.util.ArrayList;

import com.haloofwar.ecs.Entity;
import com.haloofwar.interfaces.systems.Disposable;
import com.haloofwar.interfaces.systems.Registrable;

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
