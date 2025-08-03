package com.haloofwar.enumerators.game;

public enum MusicTrack {
	MAIN("audio/music/main_theme.ogg"),
	TUTORIAL("audio/music/tutorial_theme.ogg");

	private String path;
	
	private MusicTrack(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
}
