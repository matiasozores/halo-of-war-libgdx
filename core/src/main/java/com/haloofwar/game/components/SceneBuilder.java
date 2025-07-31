package com.haloofwar.game.components;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.game.World;

public class SceneBuilder {
	public static World build(SceneType type, GameContext context, Player player) {
		MapRenderer map = new MapRenderer(type);
		WorldContext worldContext = new WorldContext(context, player, map);
		
		worldContext.getEntities().addEntity(player);
		worldContext.getCollisions().addCollidable(player);
		WorldCollisionInitializer.initializeMapColliders(map, worldContext.getCollisions());
		return new World(map, worldContext);
	}
}
