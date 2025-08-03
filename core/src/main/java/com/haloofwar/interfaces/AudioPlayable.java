package com.haloofwar.interfaces;

public interface AudioPlayable {
	void play();
	void stop();
	void setVolume(float volume);
	void pause();
	void toggleMute();
	void resume();	
	void dispose();
}
