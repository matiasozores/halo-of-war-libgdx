package com.haloofwar.enumerators;

import com.haloofwar.components.FireArmComponent;
import com.haloofwar.interfaces.EntityDescriptor;
import com.haloofwar.interfaces.Weapon;

public enum FireArmType implements Weapon, EntityDescriptor {
    RIFLE_ASALTO("Rifle de asalto", "ui/hud/armaHUD.png", 10, 25f, 0.1f, 60f),
    PISTOLA("Pistola", "ui/hud/pistolaHUD.png", 5, 30f, 0.5f, 60f);

    private final String name;
    private final String path;
    private final int damage;
    private final float speed;
    private final float fireCooldown;
    private final float bulletOffset;

    FireArmType(String name, String path, int damage, float speed, float fireCooldown, float bulletOffset) {
        this.name = name;
        this.path = path;
        this.damage = damage;
        this.speed = speed;
        this.fireCooldown = fireCooldown;
        this.bulletOffset = bulletOffset;
    }

    @Override
    public String getName() { return name; }
    
    @Override
    public String getPath() { return path; }

    public FireArmComponent createComponent() {
        return new FireArmComponent(damage, speed, fireCooldown, bulletOffset);
    }
}
