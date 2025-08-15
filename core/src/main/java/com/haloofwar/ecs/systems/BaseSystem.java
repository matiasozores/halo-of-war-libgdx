package com.haloofwar.ecs.systems;

import java.util.ArrayList;

import com.haloofwar.ecs.Entity;

public abstract class BaseSystem implements ECSSystem {

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
