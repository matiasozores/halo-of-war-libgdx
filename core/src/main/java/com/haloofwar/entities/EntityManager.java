package com.haloofwar.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.collision.CollisionManager;

public class EntityManager {
    private ArrayList<Entity> entities = new ArrayList<Entity>();
    private CollisionManager manager;
    
    public EntityManager(CollisionManager manager) {
    	this.manager = manager;
    }
    
    public void addEntity(Entity entity) {
        this.entities.add(entity);
        this.manager = manager; 
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public void update(float delta) {
        for (int i = this.entities.size() - 1; i >= 0; i--) {
            Entity entity = this.entities.get(i);
            entity.update(delta);

            if (!entity.isAlive()) {
                entity.dispose(manager);
                this.entities.remove(i); 
            }
        }
    }


    public void render(SpriteBatch batch) {
        for (Entity entity : this.entities) {
            entity.render(batch);
        }
    }

    public void dispose() {
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }
}