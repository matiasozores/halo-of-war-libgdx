package com.haloofwar.game.components;

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
        for (Entity entity : this.entities) {
            entity.update(delta);
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