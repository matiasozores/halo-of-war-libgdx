	package com.haloofwar.game.world;
	
	import java.util.Set;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.common.enumerators.LayerType;
import com.haloofwar.common.enumerators.LevelSceneType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.game.factories.ObstacleFactory;
import com.haloofwar.game.factories.PortalFactory;
	
	public class WorldCollisionInitializer {
	
	    public static void initializeMapColliders(Set<LevelSceneType> lockedLevels, final MapRenderer MAP, final TextureManager TEXTURE, final EventBus GAMEPLAY_BUS) {
	        initializeLayerEntities(lockedLevels, MAP, TEXTURE, GAMEPLAY_BUS, LayerType.PORTAL);
	        
	        initializeTileColliders(MAP, GAMEPLAY_BUS);
	    }
	
	    private static void initializeLayerEntities(Set<LevelSceneType> lockedLevels, final MapRenderer MAP, final TextureManager TEXTURE, final EventBus GAMEPLAY_BUS, final LayerType TYPE) {
	        MapLayer layer = MAP.getMetaData().getTiledMap().getLayers().get(TYPE.getName());
	        if (layer == null) {
	        	return;
	        }
	
	        for (MapObject object : layer.getObjects()) {
	            if (object instanceof RectangleMapObject) {
	                Rectangle rect = ((RectangleMapObject) object).getRectangle();
	
	                Entity entity;
	
	                if(TYPE.equals(LayerType.PORTAL)) {
	                	String teleportationTarget = object.getProperties().get("teleportation", String.class);
	                	LevelSceneType type = LevelSceneType.getLevelByName(teleportationTarget);
	                	boolean lastState;
	                	if(type != null) {
	                		if(lockedLevels.contains(type)) {
	                			lastState = false;
	                		} else {
	                			lastState = true;
	                		}
	                	} else {
	                		lastState = false;
	                	}
	                	
                    	entity = PortalFactory.create(rect, TEXTURE, teleportationTarget, lastState);
                    	GAMEPLAY_BUS.publish(new NewEntityEvent(entity));
	                }   
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
