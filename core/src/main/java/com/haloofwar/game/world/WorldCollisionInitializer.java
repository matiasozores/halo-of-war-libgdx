	package com.haloofwar.game.world;
	
	import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.common.enums.LayerType;
import com.haloofwar.common.enums.ObjectType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.game.components.CollisionComponent;
import com.haloofwar.game.factories.ObjectFactory;
import com.haloofwar.game.factories.ObstacleFactory;
import com.haloofwar.game.factories.PortalFactory;
	
	public class WorldCollisionInitializer {
	
	    public static void initializeMapColliders(final MapRenderer MAP, final TextureManager TEXTURE, final EventBus GAMEPLAY_BUS) {
	        initializeLayerEntities(MAP, TEXTURE, GAMEPLAY_BUS, LayerType.OBSTACLE);
	        initializeLayerEntities(MAP, TEXTURE, GAMEPLAY_BUS, LayerType.ITEM);
	        initializeLayerEntities(MAP, TEXTURE, GAMEPLAY_BUS, LayerType.PORTAL);
	        
	        initializeTileColliders(MAP, GAMEPLAY_BUS);
	    }
	
	    private static void initializeLayerEntities(final MapRenderer MAP, final TextureManager TEXTURE, final EventBus GAMEPLAY_BUS, final LayerType TYPE) {
	        MapLayer layer = MAP.getMetaData().getTiledMap().getLayers().get(TYPE.getName());
	        if (layer == null) {
	        	return;
	        }
	
	        for (MapObject object : layer.getObjects()) {
	            if (object instanceof RectangleMapObject) {
	                Rectangle rect = ((RectangleMapObject) object).getRectangle();
	
	                Entity entity;
	
	                switch (TYPE) {
	                    case OBSTACLE:
	                        entity = ObstacleFactory.createObstacle(rect);
	                        break;
	                    case ITEM:
	                    	entity = ObjectFactory.createItem(rect, ObjectType.generate(), TEXTURE);
	                    
	                        break;
	                    case PORTAL:
	                    	String teleportationTarget = object.getProperties().get("teleportation", String.class);
	                    	entity = PortalFactory.create(rect, TEXTURE, teleportationTarget);
							break;
	                        
	                        
	                    default:
	                        entity = new Entity();
	                        entity.addComponent(new TransformComponent(rect.x, rect.y, rect.width, rect.height));
	                        entity.addComponent(new CollisionComponent(rect.width, rect.height));
	                        break;
	                }
	
	                GAMEPLAY_BUS.publish(new NewEntityEvent(entity));
	            }
	        }
	    }
	
	    private static void initializeTileColliders(final MapRenderer MAP, final EventBus GAMEPLAY_BUS) {
	        for (MapLayer rawLayer : MAP.getMetaData().getTiledMap().getLayers()) {
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
	                            GAMEPLAY_BUS.publish(new NewEntityEvent(entity));
	                        }
	                    }
	                }
	            }
	        }
	    }
	}
