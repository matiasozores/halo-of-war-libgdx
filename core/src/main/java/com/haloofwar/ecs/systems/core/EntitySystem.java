package com.haloofwar.ecs.systems.core;

import java.util.ArrayList;

import com.haloofwar.ecs.Entity;

public class EntitySystem {
    private final ArrayList<Entity> entities;

    public EntitySystem() {
        this.entities = new ArrayList<>();
    }

    public void add(Entity entity) {
        if (entity != null && !this.entities.contains(entity)) {
            this.entities.add(entity);
        }
    }

    public void remove(Entity entity) {
        this.entities.remove(entity);
    }

    public void clear() {
        this.entities.clear();
    }
    
    public int getSize() {
    	return this.entities.size();
    }
}
