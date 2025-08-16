package com.haloofwar.ecs.systems.audio;

import com.haloofwar.dependences.audio.SoundManager;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.ItemPickedUpEvent;
import com.haloofwar.ecs.events.types.ShootBulletEvent;
import com.haloofwar.ecs.systems.EntitySystemInterface;
import com.haloofwar.enumerators.game.SoundType;

public class SoundSystem implements EntitySystemInterface{

    public SoundSystem(SoundManager sound, EventBus bus) {
        bus.subscribe(ItemPickedUpEvent.class, event -> { sound.play(SoundType.LOAD_GAME);});
        
        // Despues podemos hacer que el objeto de shootbullet tenga el atributo de tipo de sonido en especifico
        // para cuando haya mas tipos de balas con diferentes sonidos xd
        bus.subscribe(ShootBulletEvent.class, event -> { sound.play(SoundType.SHOOT_ASSAULT_RIFLE);});
    }
}
