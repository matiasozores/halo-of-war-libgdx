package com.haloofwar.ecs.systems.audio;

import com.haloofwar.dependences.audio.SoundManager;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.PlaySoundEvent;
import com.haloofwar.ecs.systems.ECSSystem;

public class SoundSystem implements ECSSystem{

    public SoundSystem(SoundManager sound, EventBus bus) {
        // Suscribirse a los eventos
        bus.subscribe(PlaySoundEvent.class, event -> sound.play(event.sound));
    }
}
