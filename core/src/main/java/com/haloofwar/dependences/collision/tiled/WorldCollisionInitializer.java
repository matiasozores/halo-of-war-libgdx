package com.haloofwar.dependences.collision.tiled;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.Entity;
import com.haloofwar.enumerators.game.LayerType;
import com.haloofwar.game.components.MapRenderer;
import com.haloofwar.interfaces.Collidable;
import com.haloofwar.interfaces.IEntityCreator;

public class WorldCollisionInitializer {

    public static void initializeMapColliders(MapRenderer map, GameContext context) {
    	// se utiliza implementaciones de IEntityCreator para crear entidades a partir de los objetos del mapa.
    	initializeLayerEntities(map, context, LayerType.OBSTACLE, new ObstacleCreator(context));
    	initializeLayerEntities(map, context, LayerType.ITEM, new ItemCreator(context));
    }

    private static void initializeLayerEntities(MapRenderer map, GameContext context, LayerType type, IEntityCreator creator) {
    	MapLayer layer = map.getMetaData().getTiledMap().getLayers().get(type.getName());
       
        if (layer == null) {
        	return;
        } 

        for (MapObject object : layer.getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                Object entity = creator.create(rect);
                
                context.getGameplay().getCollisions().add((Collidable) entity);
                context.getGameplay().getEntities().add((Entity) entity);
            }
        }
    }
}
