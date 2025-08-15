package com.haloofwar.ecs.events.types;

import com.haloofwar.enumerators.game.SoundType;

public class PlaySoundEvent {
    public final SoundType sound;

    public PlaySoundEvent(SoundType sound) {
        this.sound = sound;
    }
}
