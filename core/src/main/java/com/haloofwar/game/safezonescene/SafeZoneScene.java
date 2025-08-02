package com.haloofwar.game.safezonescene;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.game.SceneType;
import com.haloofwar.game.GameScene;

public abstract class SafeZoneScene extends GameScene{

	public SafeZoneScene(GameContext gameContext, SceneType scene, Player player) {
		super(gameContext, scene, player);
	}
}
