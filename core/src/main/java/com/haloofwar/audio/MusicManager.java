package com.haloofwar.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.haloofwar.enumerators.MusicTrack;

public class MusicManager {
	private Music currentMusic;
	private boolean musicMuted = false;
	private boolean musicLooping = true;
	private float musicVolume = 0.5f; 
	private float lastMusicVolume = 0.0f;
	
	public void playMusic(MusicTrack track) {
		if(this.currentMusic == null) {
			this.currentMusic = Gdx.audio.newMusic(Gdx.files.internal(track.getPath()));
			this.currentMusic.setLooping(this.musicLooping);
			this.currentMusic.setVolume(this.musicVolume);
			this.currentMusic.play();
		}
	}
	
	public void stopMusic() {
		if(this.currentMusic != null) {
			this.currentMusic.stop();
			this.currentMusic.dispose();
			this.currentMusic = null;
		}
	}
	
	public void setMusicVolume(float volume) {
		if(this.currentMusic != null && volume > 0.0f && volume <= 1.0f) {
			if(this.musicMuted) {
				this.musicMuted = false;
			}
			
			this.currentMusic.setVolume(volume);
			this.musicVolume = volume;
		} 
	}
	
	public void pauseMusic() {
		if(this.currentMusic != null) {
			this.currentMusic.pause();
		}
	}
	
	public void toggleMusicMute() {
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
	
	public void resumeMusic() {
		if(this.currentMusic != null) {
			this.currentMusic.play();
		}
	}
	
	public void dispose() {
		this.currentMusic.dispose();
	}
	
	public float getMusicVolume() {
		return this.musicVolume;
	}
	
	public String getMusicVolumeText() {
		return  (int)(this.musicVolume * 100) + "/100";
	}
	
	public boolean isMusicMuted() {
		return this.musicMuted;
	}
}
