package com.haloofwar.dependences.gameplay;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.entities.Entity;
import com.haloofwar.interfaces.Updatable;

public class EntityManager {

    private final ArrayList<Entity> entities = new ArrayList<>();
    private final ArrayList<Updatable> updatables = new ArrayList<>();
    
    
    public void add(Entity entity) {
        this.entities.add(entity);
        if (entity instanceof Updatable) {
            this.updatables.add((Updatable) entity);
        }
    }
    	
    public void remove(Entity entity) {
        this.entities.remove(entity);

        if (entity instanceof Updatable updatable) {
            this.updatables.remove(updatable);
        }
    }

    public void update(float delta) {
        for (int i = this.updatables.size() - 1; i >= 0; i--) {
            Updatable entity = this.updatables.get(i);
            entity.update(delta);
        }
    }

    public void render(SpriteBatch batch) {
        for (Entity entity : this.entities) {
            entity.render(batch);
        }
    }

    public void clear() {
        this.entities.clear();
        this.updatables.clear();
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }
}
