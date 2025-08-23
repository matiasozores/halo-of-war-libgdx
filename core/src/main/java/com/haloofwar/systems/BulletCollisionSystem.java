package com.haloofwar.systems;

import com.haloofwar.components.BulletComponent;
import com.haloofwar.components.Entity;
import com.haloofwar.components.HealthComponent;
import com.haloofwar.events.BulletHitEvent;
import com.haloofwar.events.CollisionEvent;
import com.haloofwar.events.DamageEvent;
import com.haloofwar.events.EventBus;
import com.haloofwar.interfaces.Registrable;

public class BulletCollisionSystem implements Registrable {

    private final EventBus bus;

    public BulletCollisionSystem(EventBus bus) {
        this.bus = bus;
        this.bus.subscribe(CollisionEvent.class, this::onCollision);
    }
    
    private void onCollision(CollisionEvent event) {
        Entity bulletEntity = null;
        Entity targetEntity = null;
        
        if(event.a.hasComponent(BulletComponent.class)) {
            bulletEntity = event.a;
            targetEntity = event.b;
        } else if(event.b.hasComponent(BulletComponent.class)) {
            bulletEntity = event.b;
            targetEntity = event.a;
        }

        if(bulletEntity == null) {
        	return;
        }
        
        final int DAMAGE = bulletEntity.getComponent(BulletComponent.class).damage;
        
        bulletEntity.getComponent(BulletComponent.class).active = false;
        this.bus.publish(new BulletHitEvent(bulletEntity, targetEntity));
        
        if (targetEntity.hasComponent(HealthComponent.class)) {
            this.bus.publish(new DamageEvent(targetEntity, DAMAGE, bulletEntity)); 
        }
    }

	@Override
	public void register(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregister(Entity entity) {
		// TODO Auto-generated method stub
		
	}

}
