package com.haloofwar.components;

import com.haloofwar.interfaces.Component;

public class MeleeWeaponComponent implements Component {
    public int damage;
    public float range;
    public float fireCooldown;
    public float cooldownTimer = 0f;
    public boolean wantsToSwing = false;

    public MeleeWeaponComponent(int damage, float range, float fireCooldown) {
        this.damage = damage;
        this.range = range;
        this.fireCooldown = fireCooldown;
    }
}
