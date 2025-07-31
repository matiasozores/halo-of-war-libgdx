package com.haloofwar.dependences.collision;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {
    private final List<Collidable> collidables = new ArrayList<>();
    private final CollisionSystem collisionSystem = new CollisionSystem();

    public void addCollidable(Collidable entity) {
        if (!this.collidables.contains(entity)) {
            this.collidables.add(entity);
        }
    }
    
    public void addCollidable(Collidable[] entity) {
    	for (int i = 0; i < entity.length; i++) {
    		  if (!this.collidables.contains(entity[i])) {
    	            this.collidables.add(entity[i]);
    	      }
		}
    }

    public void removeCollidable(Collidable entity) {
        this.collidables.remove(entity);
    }

    public void checkCollisions() {
        List<Collidable> snapshot = new ArrayList<>(this.collidables);
        int size = snapshot.size();
        for (int i = 0; i < size - 1; i++) {
            Collidable a = snapshot.get(i);
            for (int j = i + 1; j < size; j++) {
                Collidable b = snapshot.get(j);

                if (a.getBounds().overlaps(b.getBounds())) {
                    this.collisionSystem.resolveCollision(a, b);
                }
            }
        }
    }


    public void clear() {
        collidables.clear();
    }
}
