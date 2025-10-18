package com.haloofwar.game.systems;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PeacefulEvent;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.systems.weapons.FireArmBehavior;
import com.haloofwar.game.systems.weapons.MeleeWeaponBehavior;
import com.haloofwar.game.systems.weapons.WeaponBehavior;
import com.haloofwar.interfaces.Updatable;

public class WeaponSystem extends EventSystem implements Updatable {
    private final EventBus bus;
    private boolean isPeace = true;
    
    public WeaponSystem(EventBus bus) {
        this.bus = bus;
        this.bus.subscribe(PeacefulEvent.class, this::onPeaceful);
        
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(EquipmentComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
    	if(this.isPeace) {
    		return;
    	}
    	
        for (Entity entity : this.ENTITIES) {
            EquipmentComponent eq = entity.getComponent(EquipmentComponent.class);
            if (eq == null || eq.currentWeapon == null) continue;

            Entity weapon = eq.currentWeapon;

            WeaponBehavior behavior = weapon.getComponent(FireArmBehavior.class);
            
            if(behavior == null) {
            	behavior = weapon.getComponent(MeleeWeaponBehavior.class);
            }
            
            if (behavior != null) {
                behavior.update(entity, weapon, delta, this.bus);
            }
        }
    }
    
    private void onPeaceful(PeacefulEvent event) {
    	this.isPeace = event.isPeaceful;
    }
    
}
