package com.haloofwar.game.safezonescene;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.players.Player;
import com.haloofwar.enumerators.game.SceneType;

public class Tutorial extends SafeZoneScene{

	public Tutorial(GameContext gameContext, Player player) {
		super(gameContext, SceneType.TUTORIAL, player);
	}
}
