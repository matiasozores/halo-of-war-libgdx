package com.haloofwar.interfaces;

import com.haloofwar.entities.LivingEntity;

public interface DamageSoundHandler {
	void onDamage(LivingEntity entity, int damageTaken);
	void onDeath(LivingEntity entity);
}
