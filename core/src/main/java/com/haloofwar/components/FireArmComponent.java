package com.haloofwar.components;

import com.haloofwar.interfaces.Component;

public class FireArmComponent implements Component {
    public int damage;
    public float bulletSpeed;
    public float fireCooldown;
    public float cooldownTimer = 0f;
    public float bulletOffset;
    public boolean wantsToFire = false;
    public float dirX = 0f, dirY = 0f;

    public FireArmComponent(int damage, float bulletSpeed, float fireCooldown, float bulletOffset) {
        this.damage = damage;
        this.bulletSpeed = bulletSpeed;
        this.fireCooldown = fireCooldown;
        this.bulletOffset = bulletOffset;
    }
}
