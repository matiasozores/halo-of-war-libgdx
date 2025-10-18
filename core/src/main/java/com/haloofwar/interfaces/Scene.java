package com.haloofwar.interfaces;

import com.badlogic.gdx.Screen;
import com.haloofwar.common.enumerators.MusicTrack;
import com.haloofwar.game.world.World;


public interface Scene extends Screen, Updatable {
	void reconfigureCamera();
	void reset();
    default World getWorld() { return null; }
    MusicTrack getMusic();
    boolean isLevel();
}
