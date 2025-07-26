package com.haloofwar.enumerators;

public enum MusicTrack {
	MAIN_THEME("audio/music/main_theme.ogg"),
	COSTA_PERDIDA("audio/music/costa_perdida.ogg");

	private String path;
	
	private MusicTrack(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
}
