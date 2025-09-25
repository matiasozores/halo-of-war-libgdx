package com.haloofwar.interfaces;

import com.haloofwar.common.enums.MusicTrack;
import com.haloofwar.engine.entity.EntityDescriptor;
import com.haloofwar.game.cutscenes.CutSceneDataType;
import com.haloofwar.game.dependences.LevelData;

public interface SceneDescriptor extends EntityDescriptor {
	MusicTrack getMusic();
	LevelData getLevelData();
	CutSceneDataType getCutSceneType();
	boolean isLevel();
}
