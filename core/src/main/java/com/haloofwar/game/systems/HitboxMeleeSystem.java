package com.haloofwar.game.systems;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.CollisionEvent;
import com.haloofwar.engine.events.DamageEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.MeleeAttackEvent;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.utils.RandomUtils;
import com.haloofwar.game.components.CollisionComponent;
import com.haloofwar.game.components.MeleeAttackComponent;
import com.haloofwar.game.components.TransformComponent;

public class HitboxMeleeSystem extends EventSystem {

    public HitboxMeleeSystem(EventBus bus) {
    	super(bus);
        this.listenerManager.add(bus, MeleeAttackEvent.class, this::generateHitbox);
        this.listenerManager.add(bus, CollisionEvent.class, this::onCollision);
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(MeleeAttackComponent.class)) {
            super.register(e);
        }
    }
    
    private void onCollision(CollisionEvent event) {
        if (event.a.hasComponent(MeleeAttackComponent.class)) {
            this.applyMeleeDamage(event.a, event.b);
        }
        if (event.b.hasComponent(MeleeAttackComponent.class)) {
        	this.applyMeleeDamage(event.b, event.a);
        }
    }
    
    private void applyMeleeDamage(Entity source, Entity target) {
        
    	MeleeAttackComponent melee = source.getComponent(MeleeAttackComponent.class);
    	
    	if(!target.hasComponent(TransformComponent.class)) {
    		System.out.println("Ha ocurrido un error al aplicar daño a una entidad... | HitboxMeleeSystem");
    		return;
    	}
    	
    	TransformComponent tcTarget = target.getComponent(TransformComponent.class);
        if(tcTarget.identifier == melee.sourceIdentifier) {
        	return;
        }
    	
        this.bus.publish(new DamageEvent(target, melee.damage, source));
    }

    private void generateHitbox(MeleeAttackEvent event) {
        float centerX = event.x + event.width / 2;
        float centerY = event.y + event.height / 2;

        Entity hitbox = new Entity();
        hitbox.addComponent(new TransformComponent(RandomUtils.generateUniqueId(), centerX - event.range / 2, centerY - event.range / 2, event.range, event.range));
        hitbox.addComponent(new CollisionComponent(event.range, event.range, centerX, centerY));
        hitbox.addComponent(new MeleeAttackComponent(event.identifier, event.damage));

        this.bus.publish(new NewEntityEvent(hitbox));
    }
}
