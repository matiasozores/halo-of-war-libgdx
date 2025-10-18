package com.haloofwar.game.factories;

import com.haloofwar.common.enumerators.FireArmType;
import com.haloofwar.common.enumerators.MeleeWeaponType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.config.ComponentPresets;
import com.haloofwar.interfaces.Weapon;

public class WeaponFactory {	
    
    public static Entity createWeaponFromName(final String NAME) {
        Weapon weapon = FireArmType.getByName(NAME);

        if (weapon == null) {
            weapon = MeleeWeaponType.getByName(NAME);
        }

        if (weapon == null) {
            System.out.println("No se ha podido crear un arma porque el nombre no coincide... | WeaponFactory");
            return null;
        }

        return createWeapon(weapon);
    }
    
    public static Entity createWeapon(final Weapon TYPE) {
        final Entity entity = new Entity();
        entity.addComponent(ComponentPresets.defaultName(TYPE));
        entity.addComponent(ComponentPresets.defaultWeaponComponent(TYPE));
        entity.addComponent(ComponentPresets.defaultWeaponBehavior(TYPE));
        return entity;
    }
}
