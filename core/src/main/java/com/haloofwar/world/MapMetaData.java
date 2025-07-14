package com.haloofwar.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.haloofwar.enumerators.Zone;

public class MapMetaData {
	private Zone zone;
	private TiledMap tiledMap;
	
	private int tileWidth;
	private int tileHeight;
	private int mapWidth;
	private int mapHeight;
	private int mapPixelWidth;
	private int mapPixelHeight;
	
	private int xSpawnPoint;
	private int ySpawnPoint;
	
	
	public MapMetaData(Zone zone, int xSpawnPoint, int ySpawnPoint) {
		this.zone = zone;
		this.xSpawnPoint = xSpawnPoint;
		this.ySpawnPoint = ySpawnPoint;
		this.tiledMap = new TmxMapLoader().load(this.zone.getPath()); 
		
		this.tileWidth = this.tiledMap.getProperties().get("tilewidth", Integer.class);
		this.tileHeight = this.tiledMap.getProperties().get("tileheight", Integer.class);
		this.mapWidth = this.tiledMap.getProperties().get("width", Integer.class);
		this.mapHeight = this.tiledMap.getProperties().get("height", Integer.class);
		
		this.mapPixelWidth = this.mapWidth * this.tileWidth;
		this.mapPixelHeight = this.mapHeight * this.tileHeight;
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
	
	
	
}
