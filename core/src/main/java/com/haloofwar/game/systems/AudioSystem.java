package com.haloofwar.game.systems;

import com.haloofwar.common.managers.AudioManager;
import com.haloofwar.common.managers.MusicManager;
import com.haloofwar.common.managers.SoundManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.PlayMusicEvent;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.StopMusicEvent;
import com.haloofwar.engine.events.StopSoundsEvent;
import com.haloofwar.engine.interfaces.Registrable;

public class AudioSystem implements Registrable {

    private final SoundManager soundManager;
    private final MusicManager musicManager;
    private final EventListenerManager listenerGlobalManager = new EventListenerManager();
    private final EventListenerManager listenerGameplayManager = new EventListenerManager();
    
    public AudioSystem(final AudioManager audio, EventBus globalBus, EventBus gameplayBus) {
        this.soundManager = audio.getSound();
        this.musicManager = audio.getMusic();       
        this.subscribeEvents(globalBus, gameplayBus);
    }
    
    public void subscribeEvents(EventBus globalBus, EventBus gameplayBus) {
    	this.listenerGlobalManager.add(globalBus, PlaySoundEvent.class, this::onPlaySound);
    	this.listenerGlobalManager.add(globalBus, StopSoundsEvent.class, this::onStopSounds);

    	this.listenerGlobalManager.add(globalBus, PlayMusicEvent.class, this::onPlayMusic);
    	this.listenerGlobalManager.add(globalBus, StopMusicEvent.class, this::onStopMusic);
    	
    	this.listenerGameplayManager.add(gameplayBus, PlaySoundEvent.class, this::onPlaySound);
    	this.listenerGameplayManager.add(gameplayBus, StopSoundsEvent.class, this::onStopSounds);

    	this.listenerGameplayManager.add(gameplayBus, PlayMusicEvent.class, this::onPlayMusic);
    	this.listenerGameplayManager.add(gameplayBus, StopMusicEvent.class, this::onStopMusic);
    }

    // -------------------- Sonidos --------------------
    private void onPlaySound(PlaySoundEvent event) {
        this.soundManager.play(event.sound);
    }

    private void onStopSounds(StopSoundsEvent event) {
        this.soundManager.stopAll();
    }

    // -------------------- Música --------------------
    private void onPlayMusic(PlayMusicEvent event) {
        this.musicManager.play(event.track);
    }

    private void onStopMusic(StopMusicEvent event) {
        this.musicManager.stop();
    }

    // -------------------- Volumen y mute --------------------
    public void setSoundVolume(float volume) {
        this.soundManager.setVolume(volume);
    }

    public void toggleSoundMute() {
        this.soundManager.toggleMute();
    }

    public void setMusicVolume(float volume) {
        this.musicManager.setVolume(volume);
    }

    public void toggleMusicMute() {
        this.musicManager.toggleMute();
    }

    @Override
    public void register(Entity entity) {}

    @Override
    public void unregister(Entity entity) {}

    public void dispose() {
        this.soundManager.dispose();
        this.musicManager.dispose();
        this.listenerGlobalManager.clear();
        this.listenerGameplayManager.clear();
    }
}
