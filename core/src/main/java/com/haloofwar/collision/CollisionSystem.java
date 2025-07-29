package com.haloofwar.collision;

import java.util.HashMap;
import java.util.Map;

import com.haloofwar.collision.types.BulletWallCollisionHandler;
import com.haloofwar.collision.types.EntityBulletCollisionHandler;
import com.haloofwar.collision.types.EntityObjectCollisionHandler;
import com.haloofwar.collision.types.EntityWallCollisionHandler;
import com.haloofwar.enumerators.CollisionType;

public class CollisionSystem {

    private final Map<String, CollisionHandler> handlers = new HashMap<String, CollisionHandler>();

    public CollisionSystem() {
        this.registerHandlers();
    }

    private void registerHandlers() {
        this.handlers.put(this.key(CollisionType.ENTITY, CollisionType.BULLET), new EntityBulletCollisionHandler());
        this.handlers.put(this.key(CollisionType.ENTITY, CollisionType.WALL), new EntityWallCollisionHandler());
        this.handlers.put(this.key(CollisionType.ENTITY, CollisionType.OBJECT), new EntityObjectCollisionHandler());
        this.handlers.put(this.key(CollisionType.BULLET, CollisionType.WALL), new BulletWallCollisionHandler());
        this.handlers.put(this.key(CollisionType.ENTITY, CollisionType.ENTITY), new EntityWallCollisionHandler());
    }

    public void resolveCollision(Collidable a, Collidable b) {
        String key = key(a.getCollisionType(), b.getCollisionType());
        CollisionHandler handler = this.handlers.get(key);

        if (handler != null) {
            handler.handle(a, b);
        }
        else {
            key = key(b.getCollisionType(), a.getCollisionType());
            handler = this.handlers.get(key);
            if (handler != null) {
                handler.handle(b, a);
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
