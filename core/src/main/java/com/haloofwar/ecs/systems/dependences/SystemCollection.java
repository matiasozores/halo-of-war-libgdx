package com.haloofwar.ecs.systems.dependences;

import java.util.ArrayList;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.collision.BulletHitEvent;
import com.haloofwar.ecs.events.types.gameplay.SpawnBulletEvent;
import com.haloofwar.ecs.events.types.items.ItemPickedUpEvent;
import com.haloofwar.interfaces.systems.Disposable;
import com.haloofwar.interfaces.systems.Registrable;
import com.haloofwar.interfaces.systems.Renderable;
import com.haloofwar.interfaces.systems.Updatable;

public class SystemCollection {
    private final ArrayList<Updatable> UPDATE_SYSTEMS = new ArrayList<>();
    private final ArrayList<Renderable> RENDER_SYSTEMS = new ArrayList<>();
    private final ArrayList<Registrable> REGISTRY_SYSTEMS = new ArrayList<>();
    private final ArrayList<Disposable> DISPOSABLE_SYSTEMS = new ArrayList<>();
    
    private final EventBus bus; 
    
    public SystemCollection(EventBus bus) {
        this.bus = bus;
        configureEvents();
    }
    
    // Por ahora asi, es tedioso tener que agregar uno por uno pero es la mejor opcion por ahora
    private void configureEvents() {
        this.bus.subscribe(SpawnBulletEvent.class, event -> addEntity(event.bullet));
        this.bus.subscribe(ItemPickedUpEvent.class, event -> removeEntity(event.item));
        this.bus.subscribe(BulletHitEvent.class, event -> removeEntity(event.bullet));
    }
    
    public void addEntity(Entity entity) {
        for (Registrable system : this.REGISTRY_SYSTEMS) {
            system.register(entity);
        }
    }

    public void removeEntity(Entity entity) {
        for (Registrable system : this.REGISTRY_SYSTEMS) {
            system.unregister(entity);
        }
    }
    
    public ArrayList<Disposable> getDISPOSABLE_SYSTEMS() {
		return this.DISPOSABLE_SYSTEMS;
	}
    
    public ArrayList<Registrable> getREGISTRY_SYSTEMS() {
		return this.REGISTRY_SYSTEMS;
	}
    
    public ArrayList<Renderable> getRENDER_SYSTEMS() {
		return this.RENDER_SYSTEMS;
	}
    
    public ArrayList<Updatable> getUPDATE_SYSTEMS() {
		return this.UPDATE_SYSTEMS;
	}
}
