package com.haloofwar.game.components;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.dependences.collision.MapCollider;

public class WorldCollisionInitializer {

    public static void initializeMapColliders(MapRenderer map, CollisionManager collisionManager) {
        MapLayer collisionLayer = map.getMetaData().getTiledMap().getLayers().get("collision");

        if (collisionLayer == null) {
        	return;
        }

        for (MapObject object : collisionLayer.getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                MapCollider collider = new MapCollider(rect.x, rect.y, rect.width, rect.height);
                collisionManager.addCollidable(collider);
            }
        }
    }
}

