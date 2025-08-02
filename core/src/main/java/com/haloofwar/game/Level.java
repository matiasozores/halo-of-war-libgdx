package com.haloofwar.game;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.game.SceneType;

public abstract class Level extends GameScene{

	public Level(GameContext context, SceneType scene, Player player) {
		super(context, scene, player);
		// TODO Auto-generated constructor stub
	}

}
