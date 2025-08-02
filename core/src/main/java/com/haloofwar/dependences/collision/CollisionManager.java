package com.haloofwar.dependences.collision;

import java.util.ArrayList;

import com.haloofwar.dependences.gameplay.EntityManager;
import com.haloofwar.dependences.input.InputManager;

public class CollisionManager {
    private final ArrayList<Collidable> collidables = new ArrayList<>();
    private final CollisionSystem collisionSystem;
    
    public CollisionManager(InputManager input, EntityManager entities) {
		this.collisionSystem = new CollisionSystem(input, entities);
	}

    public void add(Collidable entity) {
        if (!this.collidables.contains(entity)) {
            this.collidables.add(entity);
        }
    }

    public void remove(Collidable entity) {
        this.collidables.remove(entity);
    }

    public void checkCollisions() {
        ArrayList<Collidable> snapshot = new ArrayList<>(this.collidables);
        int size = snapshot.size();
        
        // Sabemos que no es optimo
        for (int i = 0; i < size - 1; i++) {
            Collidable a = snapshot.get(i);
            for (int j = i + 1; j < size; j++) {
                Collidable b = snapshot.get(j);

                if (a.getBounds().overlaps(b.getBounds())) {
                    this.collisionSystem.resolveCollision(a, b, this);
                }
            }
        }
    }

    public void clear() {
        this.collidables.clear();
    }
}