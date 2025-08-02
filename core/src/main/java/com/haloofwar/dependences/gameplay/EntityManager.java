package com.haloofwar.dependences.gameplay;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.characters.Player;

public class EntityManager {
    private ArrayList<LivingEntity> entities = new ArrayList<LivingEntity>();
        
    public void addEntity(LivingEntity entity) {
        this.entities.add(entity);
    }

    public void removeEntity(LivingEntity entity) {
        this.entities.remove(entity);
    }

    public void update(float delta) {
        for (int i = this.entities.size() - 1; i >= 0; i--) {
            LivingEntity entity = this.entities.get(i);
            entity.update(delta);

            if(entity instanceof Player) {
            }
            
            if (!entity.isAlive()) {
                this.entities.remove(i); 
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (LivingEntity entity : this.entities) {
            entity.render(batch);
        }
    }

    public void clear() {
    	this.entities.clear();
    }

    public ArrayList<LivingEntity> getEntities() {
        return this.entities;
    }
}