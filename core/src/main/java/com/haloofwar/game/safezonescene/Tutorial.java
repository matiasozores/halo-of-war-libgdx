package com.haloofwar.game.safezonescene;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.ecs.Entity;
import com.haloofwar.enumerators.game.SceneType;

public class Tutorial extends SafeZoneScene{

	public Tutorial(GameContext gameContext, Entity player) {
		super(gameContext, SceneType.TUTORIAL, player);
	}
}
