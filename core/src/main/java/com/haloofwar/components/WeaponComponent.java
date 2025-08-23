package com.haloofwar.components;

import com.haloofwar.interfaces.Component;

public class WeaponComponent implements Component {
    public String name;
    public int damage;
    public int speed;
    public int cooldown;
    public boolean isReady = true;

    // Temporal
    public boolean wantsToFire = false;
    
    // Posibles dependencias
    public int currentCooldown = 0;

    public WeaponComponent(String name, int damage, int speed, int cooldown) {
        this.name = name;
        this.damage = damage;
        this.speed = speed;
        this.cooldown = cooldown;
    }
}
