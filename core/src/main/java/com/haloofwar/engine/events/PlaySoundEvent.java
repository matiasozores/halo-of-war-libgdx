package com.haloofwar.engine.events;

import com.haloofwar.common.enums.SoundType;

public class PlaySoundEvent {
	public final SoundType sound;

	public PlaySoundEvent(SoundType sound) {
		this.sound = sound;
	}	
}
