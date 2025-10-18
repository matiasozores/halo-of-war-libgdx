package com.haloofwar.game.components;

import com.haloofwar.engine.entity.Entity;

public class MeleeAttackComponent implements Component{
	public Entity source;
	public int damage;

	public MeleeAttackComponent(Entity source, int damage) {
		this.damage = damage;
		this.source = source;
	}
	
	
}
