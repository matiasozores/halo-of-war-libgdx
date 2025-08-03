package com.haloofwar.dependences.collision;

import java.util.ArrayList;

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
    	System.out.println("Eliminando todas las entidades colisionables");
        this.collidables.clear();
    }

    public void checkCollisions() {
        ArrayList<Collidable> snapshot = new ArrayList<>(this.collidables);
        int size = snapshot.size();

        /*
         * Sabemos que esto no es optimo, pero por ahora que hay pocas entidades nos sirve.
         * Se penso en usar Grid Espacial, pero no es necesario por ahora.
         * */
        
        for (int i = 0; i < size - 1; i++) {
            Collidable a = snapshot.get(i);
            for (int j = i + 1; j < size; j++) {
                Collidable b = snapshot.get(j);
                if (a.getBounds().overlaps(b.getBounds())) {
                	a.accept(b.getCollisionBehavior(), b);
                	b.accept(a.getCollisionBehavior(), a);
                }
            }
        }
    }
}
