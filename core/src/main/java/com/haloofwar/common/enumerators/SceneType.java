package com.haloofwar.common.enumerators;

import com.haloofwar.game.cutscenes.CutSceneDataType;
import com.haloofwar.game.dependences.LevelData;
import com.haloofwar.interfaces.SceneDescriptor;
import com.haloofwar.interfaces.SceneKey;

public enum SceneType implements SceneDescriptor, SceneKey {
    MAIN("Main", "maps/main.tmx", MusicTrack.TUTORIAL),
    CAVE("Cave", "maps/cave.tmx", MusicTrack.TUTORIAL),
    THE_LOAST_COAST("The Lost Coast", "maps/levels/TheLostCoast.tmx", MusicTrack.TUTORIAL),
    ENTRANCE_TO_TARTARUS("Entrance To Tartarus", "maps/levels/EntranceToTartarus.tmx", MusicTrack.TUTORIAL),
    SUBMERGED_TEMPLE("Submerged Temple", "maps/levels/SubmergedTemple.tmx", MusicTrack.TUTORIAL);
  
	private final String name;
    private final String path;
    private final MusicTrack music;
    
    private SceneType(final String name, final String path, final MusicTrack music) {
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
