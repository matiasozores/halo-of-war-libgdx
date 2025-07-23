package com.haloofwar.game.areas;

import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.game.Area;
import com.haloofwar.utilities.GameContext;

public class Tutorial extends Area{

	public Tutorial(GameContext gameContext, Player player) {
		super(gameContext, SceneType.ZONA_TUTORIAL, player);
	}
}
