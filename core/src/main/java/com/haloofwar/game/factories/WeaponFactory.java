package com.haloofwar.game.factories;

import com.haloofwar.common.enums.FireArmType;
import com.haloofwar.common.enums.MeleeWeaponType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.config.ComponentPresets;
import com.haloofwar.interfaces.Weapon;

public class WeaponFactory {	
	public static Entity createWeaponFromName(final String NAME) {
		Weapon weapon = null;
		
		weapon = FireArmType.getByName(NAME);
		
		if(weapon == null) {
			weapon = MeleeWeaponType.getByName(NAME);
		}
		
		if(weapon == null) {
			System.out.println("No se ha podido crear un arma porque el nombre no coincide... | WeaponFactory");
			return null;
		}
		
		return createWeapon(weapon);
	}
	
    public static Entity createWeapon(final Weapon TYPE) {
    	final Entity ENTITY = new Entity();
		ENTITY.addComponent(ComponentPresets.defaultName(TYPE));
		ENTITY.addComponent(ComponentPresets.defaultWeapon(TYPE));
		return ENTITY;
    }
}
    
