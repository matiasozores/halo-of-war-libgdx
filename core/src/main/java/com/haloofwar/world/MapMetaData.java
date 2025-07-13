package com.haloofwar.world;

import com.haloofwar.enumerators.Zone;

public class MapMetaData {
	private Zone zone;
	private int xSpawnPoint;
	private int ySpawnPoint;

	public MapMetaData(Zone zone, int xSpawnPoint, int ySpawnPoint) {
		this.zone = zone;
		this.xSpawnPoint = xSpawnPoint;
		this.ySpawnPoint = ySpawnPoint;
	}
	
}
