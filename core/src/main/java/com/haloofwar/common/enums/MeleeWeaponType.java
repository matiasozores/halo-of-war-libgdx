package com.haloofwar.common.enums;

import com.haloofwar.engine.entity.EntityDescriptor;
import com.haloofwar.game.components.MeleeWeaponComponent;
import com.haloofwar.interfaces.Weapon;

public enum MeleeWeaponType implements Weapon, EntityDescriptor {
    ESPADA("Espada", "ui/hud/espadaHUD.png", 25, 40f, 0.2f, 10f, 0f,
            "Arma versátil, buen equilibrio entre daño y velocidad.", 200),
    HACHA("Hacha", "ui/hud/hachaHUD.png", 30, 50f, 1.2f, 15f, 0f,
            "Golpes lentos pero muy poderosos, gran alcance.", 300);

    private final String name;
    private final String path;
    private final int damage;
    private final float range;
    private final float fireCooldown;
    private final float offsetX; 
    private final float offsetY;
    private final String description;
    private final int price;

    MeleeWeaponType(String name, String path, int damage, float range, float fireCooldown, float offsetX, float offsetY, String description, int price) {
        this.name = name;
        this.path = path;
        this.damage = damage;
        this.range = range;
        this.fireCooldown = fireCooldown;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getName() { return this.name; }
    @Override
    public String getPath() { return this.path; }
    
    public MeleeWeaponComponent createComponent() {
        return new MeleeWeaponComponent(this.damage, this.range, this.fireCooldown, this.offsetX, this.offsetY, this);
    }

    @Override
    public String getDescription() { return this.description; }

    @Override
    public int getPrice() { return this.price; }
    
    public static MeleeWeaponType getByName(final String NAME) {
    	boolean found = false;
    	MeleeWeaponType type = null;
    	int i = 0;
    	final MeleeWeaponType MELEE_WEAPONS[] = MeleeWeaponType.values();
    	
    	while(i < MELEE_WEAPONS.length && !found) {
    		if(MELEE_WEAPONS[i].getName().equals(NAME)) {
    			found = true;
    			type = MELEE_WEAPONS[i];
    		} else {
    			i++;
    		}
    	}
    	
    	return type;
    }
}
