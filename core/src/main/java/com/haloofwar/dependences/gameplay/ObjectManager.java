package com.haloofwar.dependences.gameplay;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.entities.statics.Item;

public class ObjectManager {
    private ArrayList<Item> objects = new ArrayList<Item>();
        
    public void addObject(Item item) {
        this.objects.add(item);
    }

    public void removeObject(Item item) {
        this.objects.remove(item);
    }

    public void render(SpriteBatch batch) {
    
    	for (Item item: this.objects) {
            item.render(batch);
        }
    }

    public void clear() {
    	this.objects.clear();
    }

    public ArrayList<Item> getObjects() {
        return this.objects;
    }
}