package com.haloofwar.common.enumerators;

public enum MusicTrack {
	MAIN("audio/music/main_theme.ogg"),
	TUTORIAL("audio/music/tutorial_theme.ogg"),
	VICTORY("audio/music/victory.wav"),
	CUTSCENE("audio/music/cutscene.wav");

	private final String path;
	
	private MusicTrack(final String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
}
