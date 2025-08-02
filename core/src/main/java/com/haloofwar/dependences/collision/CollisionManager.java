package com.haloofwar.dependences.collision;

import java.util.ArrayList;

import com.haloofwar.dependences.gameplay.ObjectManager;
import com.haloofwar.dependences.input.InputManager;

public class CollisionManager {
    private final ArrayList<Collidable> collidables = new ArrayList<>();
    private final CollisionSystem collisionSystem;
    
    public CollisionManager(InputManager input, ObjectManager objects) {
		this.collisionSystem = new CollisionSystem(input, objects);
	}

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
        ArrayList<Collidable> snapshot = new ArrayList<>(this.collidables);
        int size = snapshot.size();
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
        collidables.clear();
    }
}
