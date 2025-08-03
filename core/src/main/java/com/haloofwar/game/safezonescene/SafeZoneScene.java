package com.haloofwar.game.safezonescene;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.players.Player;
import com.haloofwar.enumerators.game.SceneType;
import com.haloofwar.game.GameScene;

public abstract class SafeZoneScene extends GameScene{

	// Es otra clase de escena que no es un mundo, sino una zona segura como puede ser el tutorial, lobby,
	// un pueblo de npcs, etc. La gracia de esta clase es que el jugador no puede morir en estas zonas.
	public SafeZoneScene(GameContext gameContext, SceneType scene, Player player) {
		super(gameContext, scene, player);
	}
}
