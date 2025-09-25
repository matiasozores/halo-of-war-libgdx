package com.haloofwar.common.enums;

import com.haloofwar.game.cutscenes.CutSceneDataType;
import com.haloofwar.game.dependences.LevelData;
import com.haloofwar.interfaces.SceneDescriptor;
import com.haloofwar.interfaces.SceneKey;

public enum SceneType implements SceneDescriptor, SceneKey {
    MAIN("Main", "maps/main.tmx", MusicTrack.TUTORIAL),
    CAVE("Cave", "maps/cave.tmx", MusicTrack.VICTORY),;
  
	private final String name;
    private final String path;
    private final MusicTrack music;
    
    private SceneType(String name, String path, MusicTrack music) {
		this.name = name;
		this.path = path;
		this.music = music;
	}
    
    private SceneType(String name, String path) {
    	this(name, path, null);
	}

	public String getName() {
		return this.name;
	}

	public String getPath() {
		return this.path;
	}
	
	public MusicTrack getMusic() {
		return this.music;
	}

	@Override
	public LevelData getLevelData() {
		return null;
	}

	@Override
	public CutSceneDataType getCutSceneType() {
		return null;
	}

	@Override
	public boolean isLevel() {
		return false;
	}

	@Override
	public SceneType getScene() {
		return this;
	}
}
