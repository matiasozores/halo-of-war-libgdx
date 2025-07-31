package com.haloofwar.dependences.gameplay;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.entities.Entity;

public class EntityManager {
    private ArrayList<Entity> entities = new ArrayList<Entity>();
        
    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    public void update(float delta) {
        for (int i = this.entities.size() - 1; i >= 0; i--) {
            Entity entity = this.entities.get(i);
            entity.update(delta);

            if (!entity.isAlive()) {
            	System.out.println("Enemigo elminado, falta dispose()");
                this.entities.remove(i); 
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Entity entity : this.entities) {
            entity.render(batch);
        }
    }

    public void clear() {
    	this.entities.clear();
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }
}