package com.haloofwar.game;

import com.haloofwar.components.Entity;
import com.haloofwar.ui.HUD;

public abstract class SafeZoneScene extends GameScene{

	// Es otra clase de escena que no es un mundo, sino una zona segura como puede ser el tutorial, lobby,
	// un pueblo de npcs, etc. La gracia de esta clase es que el jugador no puede morir en estas zonas.
	public SafeZoneScene(World world, HUD hud, Entity player) {
		super(world, hud, player);
	}
}
