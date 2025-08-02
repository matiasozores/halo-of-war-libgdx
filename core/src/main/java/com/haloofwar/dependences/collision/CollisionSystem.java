package com.haloofwar.dependences.collision;

import java.util.HashMap;
import java.util.Map;

import com.haloofwar.dependences.collision.types.BulletWallCollisionHandler;
import com.haloofwar.dependences.collision.types.EntityBulletCollisionHandler;
import com.haloofwar.dependences.collision.types.EntityDetectionCollisionHandler;
import com.haloofwar.dependences.collision.types.EntityObjectCollisionHandler;
import com.haloofwar.dependences.collision.types.EntityPickupCollisionHandler;
import com.haloofwar.dependences.collision.types.EntityWallCollisionHandler;
import com.haloofwar.dependences.gameplay.ObjectManager;
import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.enumerators.entities.behavior.CollisionType;

public class CollisionSystem {

    private final Map<String, CollisionHandler> handlers = new HashMap<String, CollisionHandler>();
    private InputManager input;
    private ObjectManager objects;
    
    public CollisionSystem(InputManager input, ObjectManager objects) {
		this.input = input;
		this.objects = objects;
		this.registerHandlers();
    }

    private void registerHandlers() {
        this.handlers.put(this.key(CollisionType.ENTITY, CollisionType.BULLET), new EntityBulletCollisionHandler());
        this.handlers.put(this.key(CollisionType.ENTITY, CollisionType.OBSTACLE), new EntityWallCollisionHandler());
        this.handlers.put(this.key(CollisionType.ENTITY, CollisionType.OBJECT), new EntityObjectCollisionHandler());
        this.handlers.put(this.key(CollisionType.BULLET, CollisionType.OBSTACLE), new BulletWallCollisionHandler());
        this.handlers.put(this.key(CollisionType.ENTITY, CollisionType.ENTITY), new EntityWallCollisionHandler());
        this.handlers.put(this.key(CollisionType.ENTITY, CollisionType.PICKUP), new EntityPickupCollisionHandler(this.input, this.objects));
        this.handlers.put(this.key(CollisionType.ENTITY, CollisionType.ZONE_DETECTION), new EntityDetectionCollisionHandler());
        
    }

    public void resolveCollision(Collidable a, Collidable b, CollisionManager manager) {
        String key = key(a.getCollisionType(), b.getCollisionType());
        CollisionHandler handler = this.handlers.get(key);

        if (handler != null) {
            handler.handle(a, b, manager);
        }
        else {
            key = key(b.getCollisionType(), a.getCollisionType());
            handler = this.handlers.get(key);
            if (handler != null) {
                handler.handle(b, a, manager);
            }
        }
    }

    private String key(CollisionType a, CollisionType b) {
        if (a.ordinal() < b.ordinal()) {
            return a.name() + "-" + b.name();
        } else {
            return b.name() + "-" + a.name();
        }
        
      
    }

}
