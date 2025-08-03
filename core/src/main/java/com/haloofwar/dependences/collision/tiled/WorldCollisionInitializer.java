package com.haloofwar.dependences.collision.tiled;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.components.EntityStateHandler;
import com.haloofwar.entities.statics.Item;
import com.haloofwar.entities.statics.Obstacle;
import com.haloofwar.factories.ItemsFactory;
import com.haloofwar.game.components.MapRenderer;

public class WorldCollisionInitializer {
	
    public static void initializeMapColliders(MapRenderer map, GameContext context) {
        MapLayer collisionLayer = map.getMetaData().getTiledMap().getLayers().get("collision");
        
        if (collisionLayer == null) {
        	return;
        }
     
        for (MapObject object : collisionLayer.getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                EntityStateHandler state = new EntityStateHandler(context.getGameplay().getCollisions(), context.getGameplay().getEntities());
                Obstacle wall = new Obstacle(rect.x, rect.y, (int) rect.width, (int) rect.height, state);
                context.getGameplay().getCollisions().add(wall);
                context.getGameplay().getEntities().add(wall);
            }
        }
        
        
        MapLayer itemsLayer = map.getMetaData().getTiledMap().getLayers().get("items");

        if (itemsLayer == null) {
        	return;
        }
        
     
        for (MapObject object : itemsLayer.getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                ItemsFactory itemsFactory = new ItemsFactory(context);
                Item item = itemsFactory.create(rect.x, rect.y, (int) rect.width, (int) rect.height);
                context.getGameplay().getCollisions().add(item);
                context.getGameplay().getEntities().add(item);
            }
        }
        
    }
}

