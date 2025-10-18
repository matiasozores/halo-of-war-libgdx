package com.haloofwar.game.components;

import com.haloofwar.common.enumerators.FireArmType;
import com.haloofwar.interfaces.Weapon;
import com.haloofwar.interfaces.WeaponComponent;

public class FireArmComponent implements Component, WeaponComponent {
    public int damage;
    public float bulletSpeed;
    public float fireCooldown;
    public float cooldownTimer = 0f;
    public float bulletOffset;
    public boolean wantsToFire = false;
    public float dirX = 0f, dirY = 0f;
    public FireArmType type;

    public FireArmComponent(int damage, float bulletSpeed, float fireCooldown, float bulletOffset, FireArmType type) {
        this.damage = damage;
        this.bulletSpeed = bulletSpeed;
        this.fireCooldown = fireCooldown;
        this.bulletOffset = bulletOffset;
        this.type = type;
    }
    
    @Override
	public Weapon getWeapon() {
		return this.type;
	}
}
