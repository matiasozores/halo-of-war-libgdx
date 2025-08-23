package com.haloofwar.dependences;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.haloofwar.enumerators.SoundType;

public class SoundManager {
    private final HashMap<SoundType, Sound> soundMap = new HashMap<>();
    private boolean muted = false;
    private float volume = 0.15f;
    private float lastVolume = 0.5f;

    public SoundManager() {
    	this.load(SoundType.CLICK);
    	this.load(SoundType.ENTER);
    	this.load(SoundType.LOAD_GAME);
    	this.load(SoundType.WALK);
    }
    
    public void load(SoundType soundType) {
        try {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal(soundType.getPath()));
            this.soundMap.put(soundType, sound);
        } catch (Exception e) {
        	 System.out.println("Error al cargar el sonido: " + soundType + " (" + soundType.getPath() + ")");
             e.printStackTrace();
        }
    }


    public void play(SoundType soundType) {
        if (this.muted) {
        	return;
        }

        Sound sound = this.soundMap.get(soundType);
        
        if (sound != null) {
            sound.play(this.volume);
        } else {
            this.load(soundType);
            sound = this.soundMap.get(soundType);
            if (sound != null) {
                sound.play(this.volume);
            } else {
            	System.out.println("No se pudo reproducir el sonido: " + soundType);
            }
        }
    }
    
    public void stopAll() {
        for (Sound sound : this.soundMap.values()) {
            sound.stop();
        }
    }


    public void setVolume(float newVolume) {
        if (newVolume >= 0 && newVolume <= 1) {
            this.volume = newVolume;
            if (this.muted) {
                this.lastVolume = newVolume;
                this.muted = false;
            }
        }
    }

	public void toggleMute() {
		if(this.muted) {
			this.volume = this.lastVolume;
			this.muted = false;
		} else {
			this.lastVolume = this.volume;
			this.volume = 0;
			this.muted = true;
		}
	}

    public float getVolume() {
        return this.volume;
    }

    public String getVolumeText() {
        return (int)(this.volume * 100) + "/100";
    }
    
	public boolean isMuted() {
		return this.muted;
	}

    public void dispose() {
        for (Sound sound : this.soundMap.values()) {
            sound.dispose();
        }
        this.soundMap.clear();
    }
}
