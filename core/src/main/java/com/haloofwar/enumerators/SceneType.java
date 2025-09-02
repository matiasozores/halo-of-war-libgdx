package com.haloofwar.enumerators;

public enum SceneType {
    MAIN("Main", "maps/main.tmx", MusicTrack.TUTORIAL),
    CAVE("Cave", "maps/cave.tmx", MusicTrack.TUTORIAL),;
  
	private String name;
    private String path;
    private MusicTrack music;
    
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
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public MusicTrack getMusic() {
		return this.music;
	}
}
