package com.haloofwar.game.components;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.collision.CollisionComponent;
import com.haloofwar.ecs.components.physics.TransformComponent;
import com.haloofwar.enumerators.entities.objects.ObjectType;
import com.haloofwar.enumerators.game.LayerType;
import com.haloofwar.factories.ObjectFactory;
import com.haloofwar.factories.ObstacleFactory;

public class WorldCollisionInitializer {

    public static void initializeMapColliders(MapRenderer map, GameContext context) {
        // Se crean entidades a partir de las capas del mapa
        initializeLayerEntities(map, context, LayerType.OBSTACLE);
        initializeLayerEntities(map, context, LayerType.ITEM);
    }

    private static void initializeLayerEntities(MapRenderer map, GameContext context, LayerType type) {
        MapLayer layer = map.getMetaData().getTiledMap().getLayers().get(type.getName());
        if (layer == null) return;

        for (MapObject object : layer.getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                Entity entity;

                // Crear entidad según el tipo de capa
                switch (type) {
                    case OBSTACLE:
                        entity = ObstacleFactory.createObstacle(rect);
                        break;
                    case ITEM:
                        entity = ObjectFactory.createItem(rect, ObjectType.POSION_SIN_EFECTOS, context.getTexture()); // Ejemplo: HealthPotion
                        break;
                    // Agregar más casos si tienes otros tipos de capas
                    default:
                        entity = new Entity();
                        entity.addComponent(new TransformComponent(rect.x, rect.y, rect.width, rect.height));
                        entity.addComponent(new CollisionComponent(new Rectangle(rect.x, rect.y, rect.width, rect.height)));
                        break;
                }

                context.getGameplay().addEntity(entity);
            }
        }
    }

}

