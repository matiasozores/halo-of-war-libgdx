package com.haloofwar.dependences.collision;

import java.util.ArrayList;

import com.haloofwar.entities.Entity;
import com.haloofwar.interfaces.Collidable;

public class CollisionManager {
    private final ArrayList<Collidable> collidables = new ArrayList<>();

    public void add(Collidable entity) {
        if (!this.collidables.contains(entity)) {
            this.collidables.add(entity);
        }
    }

    public void remove(Collidable entity) {
        this.collidables.remove(entity);
    }

    public void clear() {
        this.collidables.clear();
    }

    public void checkCollisions() {
        ArrayList<Collidable> snapshot = new ArrayList<>(this.collidables);
        int size = snapshot.size();

        for (int i = 0; i < size - 1; i++) {
            Collidable a = snapshot.get(i);
            for (int j = i + 1; j < size; j++) {
                Collidable b = snapshot.get(j);
                if (a.getBounds().overlaps(b.getBounds())) {
                	a.accept(b.getCollisionBehavior(), (Entity) b);
                	b.accept(a.getCollisionBehavior(), (Entity) a);
                }
            }
        }
    }
}
