package com.haloofwar.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.haloofwar.enumerators.SceneType;

public class MapMetaData {
	private SceneType zone;
	private TiledMap tiledMap;
	
	private int tileWidth;
	private int tileHeight;
	private int mapWidth;
	private int mapHeight;
	private int mapPixelWidth;
	private int mapPixelHeight;
	
	private Integer xSpawnPoint;
	private Integer ySpawnPoint;
	
	
	public MapMetaData(SceneType zone) {
		this.zone = zone;
		this.tiledMap = new TmxMapLoader().load(this.zone.getPath()); 
		
		this.tileWidth = this.tiledMap.getProperties().get("tilewidth", Integer.class);
		this.tileHeight = this.tiledMap.getProperties().get("tileheight", Integer.class);
		this.mapWidth = this.tiledMap.getProperties().get("width", Integer.class);
		this.mapHeight = this.tiledMap.getProperties().get("height", Integer.class);
		
		this.mapPixelWidth = this.mapWidth * this.tileWidth;
		this.mapPixelHeight = this.mapHeight * this.tileHeight;
		
		// Asumiendo que tu layer de spawn se llama "Spawn"
		this.xSpawnPoint = this.tiledMap.getProperties().get("xSpawnPoint", Integer.class);
		this.ySpawnPoint = this.tiledMap.getProperties().get("ySpawnPoint", Integer.class);

		// Debugging output
		if (this.xSpawnPoint == null || this.ySpawnPoint == null) {
			System.err.println("Warning: Spawn point properties not found in the map properties.");
			this.xSpawnPoint = 0;
			this.ySpawnPoint = 0;
		}

	}
	
	public TiledMap getTiledMap() {
		return this.tiledMap;
	}
	
	public int getMapPixelWidth() {
		return this.mapPixelWidth;
	}
	
	public int getMapPixelHeight() {
		return this.mapPixelHeight;
	}
	
	public float getxSpawnPoint() {
		return this.xSpawnPoint;
	}
	
	public float getySpawnPoint() {
		return this.ySpawnPoint;
	}
	
	public void dispose() {
		this.tiledMap.dispose();
	}
	
}
