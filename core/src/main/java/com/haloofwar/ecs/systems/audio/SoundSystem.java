package com.haloofwar.ecs.systems.audio;

import com.haloofwar.dependences.audio.SoundManager;
import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.gameplay.PlayerDiedEvent;
import com.haloofwar.ecs.events.types.gameplay.ShootBulletEvent;
import com.haloofwar.ecs.events.types.general.NavigationEvent;
import com.haloofwar.ecs.events.types.general.SelectOptionEvent;
import com.haloofwar.ecs.events.types.items.ItemPickedUpEvent;
import com.haloofwar.enumerators.game.SoundType;
import com.haloofwar.interfaces.systems.Registrable;

public class SoundSystem implements Registrable {
	
	private final SoundManager SOUND;
	
    public SoundSystem(SoundManager sound, EventBus bus) {
    	this.SOUND = sound;
        bus.subscribe(ItemPickedUpEvent.class, event -> play(SoundType.LOAD_GAME));
        bus.subscribe(ShootBulletEvent.class, event -> play(SoundType.SHOOT_ASSAULT_RIFLE));
        bus.subscribe(PlayerDiedEvent.class, event -> play(SoundType.GAME_OVER));
        bus.subscribe(NavigationEvent.class, event -> play(SoundType.CLICK));
        bus.subscribe(SelectOptionEvent.class, event -> play(SoundType.ENTER));
    }
    
    private void play(SoundType type) {
    	this.SOUND.play(type);
    }

	@Override
	public void register(Entity entity) {
		
	}

	@Override
	public void unregister(Entity entity) {

	}
}
