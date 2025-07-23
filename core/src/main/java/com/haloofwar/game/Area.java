package com.haloofwar.game;

import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.utilities.GameContext;

public abstract class Area extends GameScene{

	public Area(GameContext gameContext, SceneType scene, Player player) {
		super(gameContext, scene, player);
	}
}
