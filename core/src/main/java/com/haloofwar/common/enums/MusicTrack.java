package com.haloofwar.common.enums;

public enum MusicTrack {
	MAIN("audio/music/main_theme.ogg"),
	TUTORIAL("audio/music/tutorial_theme.ogg"),
	VICTORY("audio/music/victory.wav"),
	CUTSCENE("audio/music/cutscene.wav");

	private String path;
	
	private MusicTrack(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
}
