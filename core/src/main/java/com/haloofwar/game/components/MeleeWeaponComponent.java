package com.haloofwar.game.components;

import com.haloofwar.common.enumerators.MeleeWeaponType;
import com.haloofwar.interfaces.Weapon;
import com.haloofwar.interfaces.WeaponComponent;

public class MeleeWeaponComponent implements Component, WeaponComponent {
    public int damage;
    public float range;         // Alcance del ataque
    public float fireCooldown;  // Tiempo entre ataques
    public float cooldownTimer = 0f;
    public boolean wantsToSwing = false;
    public MeleeWeaponType type;

    // Offset relativo al jugador para calcular la hitbox
    public float offsetX;
    public float offsetY;

    public MeleeWeaponComponent(int damage, float range, float fireCooldown, float offsetX, float offsetY, MeleeWeaponType type) {
        this.damage = damage;
        this.range = range;
        this.fireCooldown = fireCooldown;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.type = type;
    }

	@Override
	public Weapon getWeapon() {
		return this.type;
	}
}
