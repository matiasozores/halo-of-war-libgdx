package com.haloofwar.world.levels;

import java.util.HashMap;
import java.util.Map;

import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.Zone;
import com.haloofwar.world.MapMetaData;

public class Lobby extends Level {

	public Lobby(Player player1) {
		super(player1, Zone.ZONA_COSTA_PERDIDA, generateMaps());
	}
	
	private static Map<Zone, MapMetaData> generateMaps() {
		Map<Zone, MapMetaData> maps = new HashMap<>();
		maps.put(Zone.ZONA_COSTA_PERDIDA, new MapMetaData(Zone.ZONA_COSTA_PERDIDA, 0, 0));
		return maps;
	}
}
