package com.haloofwar.dependences;

public class AudioManager {
	private final SoundManager sound;
	private final MusicManager music;
	
	public AudioManager() {
		this.sound = new SoundManager();
		this.music = new MusicManager();
	}
	
	public SoundManager getSound() {
		return this.sound;
	}
	
	public MusicManager getMusic() {
		return this.music;
	}
	
	public void dispose() {
		this.sound.dispose();
		this.music.dispose();
	}
}
