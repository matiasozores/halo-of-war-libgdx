package com.haloofwar.game;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.SceneType;

public abstract class Area extends GameScene{

	public Area(GameContext gameContext, SceneType scene, Player player) {
		super(gameContext, scene, player);
	}
}
