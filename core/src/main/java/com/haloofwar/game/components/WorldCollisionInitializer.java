package com.haloofwar.game.components;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
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
        // Procesa capas de objetos (ObjectLayers)
        initializeLayerEntities(map, context, LayerType.OBSTACLE);
        initializeLayerEntities(map, context, LayerType.ITEM);

        // Procesa colisiones definidas en los tiles de tilesets (tsx)
        initializeTileColliders(map, context);
    }

    /**
     * Maneja las capas de objetos dibujadas en el mapa (ObjectLayers).
     */
    private static void initializeLayerEntities(MapRenderer map, GameContext context, LayerType type) {
        MapLayer layer = map.getMetaData().getTiledMap().getLayers().get(type.getName());
        if (layer == null) return;

        for (MapObject object : layer.getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                Entity entity;

                switch (type) {
                    case OBSTACLE:
                        entity = ObstacleFactory.createObstacle(rect);
                        break;
                    case ITEM:
                        entity = ObjectFactory.createItem(
                                rect,
                                ObjectType.POSION_SIN_EFECTOS,
                                context.getTexture()
                        );
                        break;
                    default:
                        entity = new Entity();
                        entity.addComponent(new TransformComponent(rect.x, rect.y, rect.width, rect.height));
                        entity.addComponent(new CollisionComponent(rect.width, rect.height));
                        break;
                }

                context.getGameplay().addEntity(entity);
            }
        }
    }

    /**
     * Procesa las colisiones definidas dentro de los tiles de un tileset (tsx).
     */
    private static void initializeTileColliders(MapRenderer map, GameContext context) {
        for (MapLayer rawLayer : map.getMetaData().getTiledMap().getLayers()) {
            if (!(rawLayer instanceof TiledMapTileLayer)) continue;

            TiledMapTileLayer tileLayer = (TiledMapTileLayer) rawLayer;

            for (int x = 0; x < tileLayer.getWidth(); x++) {
                for (int y = 0; y < tileLayer.getHeight(); y++) {
                    TiledMapTileLayer.Cell cell = tileLayer.getCell(x, y);
                    if (cell == null) continue;

                    TiledMapTile tile = cell.getTile();
                    if (tile == null) continue;

                    // Objetos de colisión definidos en el editor de tiles (tsx)
                    for (MapObject object : tile.getObjects()) {
                        if (object instanceof RectangleMapObject) {
                            Rectangle rect = ((RectangleMapObject) object).getRectangle();

                            // Ajustar a coordenadas del mundo
                            Rectangle worldRect = new Rectangle(
                                    x * tileLayer.getTileWidth() + rect.x,
                                    y * tileLayer.getTileHeight() + rect.y,
                                    rect.width,
                                    rect.height
                            );

                            // Crear obstáculo genérico
                            Entity entity = ObstacleFactory.createObstacle(worldRect);
                            context.getGameplay().addEntity(entity);
                        }
                    }
                }
            }
        }
    }
}
