package com.haloofwar.game.components;

public class MeleeAttackComponent implements Component{
	public int damage;
	public final int sourceIdentifier;

	public MeleeAttackComponent(final int sourceIdentifier, int damage) {
		this.sourceIdentifier = sourceIdentifier;
		this.damage = damage;
	}
	
	
}
