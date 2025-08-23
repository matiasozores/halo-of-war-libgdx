package com.haloofwar.game.dependences;

import com.haloofwar.components.Entity;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.game.World;

public class SceneBuilder {
	public static World build(SceneType type, GameContext context, Entity player) {
		MapRenderer map = new MapRenderer(type);
		WorldContext worldContext = new WorldContext(player, map, context);
	
		// Se encarga de agregar las collisiones del mapa al collision manager
		WorldCollisionInitializer.initializeMapColliders(map, context);
		return new World(map, worldContext);
	}
}
