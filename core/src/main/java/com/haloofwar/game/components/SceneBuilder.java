package com.haloofwar.game.components;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.collision.tiled.WorldCollisionInitializer;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.game.SceneType;
import com.haloofwar.game.World;

public class SceneBuilder {
	public static World build(SceneType type, GameContext context, Player player) {
		MapRenderer map = new MapRenderer(type);
		WorldContext worldContext = new WorldContext(player, map, context);
	
		worldContext.getGameplay().getEntities().addEntity(player);
		worldContext.getGameplay().getCollisions().addCollidable(player);

		WorldCollisionInitializer.initializeMapColliders(map, context);
		return new World(map, worldContext);
	}
}
