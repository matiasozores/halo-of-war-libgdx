package com.haloofwar.enumerators;

import com.haloofwar.components.MeleeWeaponComponent;
import com.haloofwar.interfaces.Weapon;

public enum MeleeWeaponType implements Weapon {
    ESPADA("Espada", "ui/hud/espadaHUD.png", 25, 40f, 1.0f),
    HACHA("Hacha", "ui/hud/hachaHUD.png", 30, 50f, 1.2f);

    private final String name;
    private final String path;
    private final int damage;
    private final float range;
    private final float fireCooldown;

    MeleeWeaponType(String name, String path, int damage, float range, float fireCooldown) {
        this.name = name;
        this.path = path;
        this.damage = damage;
        this.range = range;
        this.fireCooldown = fireCooldown;
    }

    public String getName() { return name; }
    public String getPath() { return path; }

    public MeleeWeaponComponent createComponent() {
        return new MeleeWeaponComponent(damage, range, fireCooldown);
    }
}
