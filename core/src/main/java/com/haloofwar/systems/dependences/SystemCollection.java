package com.haloofwar.systems.dependences;

import java.util.ArrayList;

import com.haloofwar.components.Entity;
import com.haloofwar.interfaces.Disposable;
import com.haloofwar.interfaces.Registrable;
import com.haloofwar.interfaces.Renderable;
import com.haloofwar.interfaces.Updatable;

public class SystemCollection {
    private final ArrayList<Updatable> UPDATE_SYSTEMS = new ArrayList<>();
    private final ArrayList<Renderable> RENDER_SYSTEMS = new ArrayList<>();
    private final ArrayList<Registrable> REGISTRY_SYSTEMS = new ArrayList<>();
    private final ArrayList<Disposable> DISPOSABLE_SYSTEMS = new ArrayList<>();
    
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
