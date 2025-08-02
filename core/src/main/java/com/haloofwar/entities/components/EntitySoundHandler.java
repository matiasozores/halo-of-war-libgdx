package com.haloofwar.entities.components;

import com.haloofwar.dependences.audio.SoundManager;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.statics.Item;
import com.haloofwar.enumerators.game.SoundType;
import com.haloofwar.interfaces.DamageSoundHandler;
import com.haloofwar.interfaces.InteractionSoundHandler;

public class EntitySoundHandler implements DamageSoundHandler, InteractionSoundHandler {
	private final SoundManager sound;
	
	public EntitySoundHandler(SoundManager sound) {
		this.sound = sound;
	}
	
	@Override
	public void onDamage(LivingEntity entity, int damageTaken) {
		   this.sound.play(SoundType.ENTER); 
	}

	@Override
	public void onDeath(LivingEntity entity) {
		   this.sound.play(SoundType.LOAD_GAME); 
	}

	@Override
	public void onItemPickup(Item item) {
		this.sound.play(SoundType.ITEM_PICKUP);
	}

}
