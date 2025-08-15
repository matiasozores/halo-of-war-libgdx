package com.haloofwar.game.components;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.ecs.Entity;
import com.haloofwar.enumerators.game.SceneType;
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
