package com.haloofwar.dependences.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.haloofwar.enumerators.game.MusicTrack;

public class MusicManager {
	private Music currentMusic;
	private boolean musicMuted = false;
	private boolean musicLooping = true;
	private float musicVolume = 0.15f; 
	private float lastMusicVolume = 0.0f;
	
	public void play(MusicTrack track) {
		if(this.currentMusic == null) {
			this.currentMusic = Gdx.audio.newMusic(Gdx.files.internal(track.getPath()));
			this.currentMusic.setLooping(this.musicLooping);
			this.currentMusic.setVolume(this.musicVolume);
			this.currentMusic.play();
		}
	}
	
	public void stop() {
		if(this.currentMusic != null) {
			this.currentMusic.stop();
			this.currentMusic.dispose();
			this.currentMusic = null;
		}
	}
	
	public void setVolume(float volume) {
		if(this.currentMusic != null && volume > 0.0f && volume <= 1.0f) {
			if(this.musicMuted) {
				this.musicMuted = false;
			}
			
			this.currentMusic.setVolume(volume);
			this.musicVolume = volume;
		} 
	}
	
	public void pause() {
		if(this.currentMusic != null) {
			this.currentMusic.pause();
		}
	}
	
	public void toggleMute() {
		if(this.currentMusic != null) {
			if(this.musicMuted) {
				this.currentMusic.setVolume(this.lastMusicVolume);
				this.musicVolume = this.lastMusicVolume;
				this.musicMuted = false;
			} else {
				this.currentMusic.setVolume(0.0f);
				this.lastMusicVolume = this.musicVolume;
				this.musicVolume = 0.0f;
				this.musicMuted = true;
			}
		}
	}
	
	public void resume() {
		if(this.currentMusic != null) {
			this.currentMusic.play();
		}
	}
	
	public float getVolume() {
		return this.musicVolume;
	}
	
	public String getVolumeText() {
		return  (int)(this.musicVolume * 100) + "/100";
	}
	
	public boolean isMuted() {
		return this.musicMuted;
	}
	
	public void dispose() {
		this.currentMusic.dispose();
	}
}
