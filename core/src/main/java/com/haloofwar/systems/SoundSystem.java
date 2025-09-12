package com.haloofwar.systems;

import com.haloofwar.components.Entity;
import com.haloofwar.dependences.SoundManager;
import com.haloofwar.enumerators.SoundType;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.NavigationSoundEvent;
import com.haloofwar.events.PickupSoundEvent;
import com.haloofwar.events.PlayerDiedEvent;
import com.haloofwar.events.SelectOptionSoundEvent;
import com.haloofwar.events.ShootBulletEvent;
import com.haloofwar.interfaces.Registrable;

public class SoundSystem implements Registrable {
	
	private final SoundManager SOUND;
	
    public SoundSystem(SoundManager sound, EventBus bus) {
    	this.SOUND = sound;
        bus.subscribe(NavigationSoundEvent.class, event -> play(SoundType.CLICK));
        bus.subscribe(SelectOptionSoundEvent.class, event -> play(SoundType.ENTER));
    }
    
    public void setGameplayBus(EventBus bus) {
		bus.subscribe(ShootBulletEvent.class, event -> play(SoundType.SHOOT_ASSAULT_RIFLE));
		bus.subscribe(PlayerDiedEvent.class, event -> play(SoundType.GAME_OVER));	
		bus.subscribe(PickupSoundEvent.class, event -> play(SoundType.ITEM_PICKUP));
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
