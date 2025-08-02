package com.haloofwar.weapons.guns;

import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.interfaces.Shooter;
import com.haloofwar.weapons.Weapon;

public abstract class Gun extends Weapon {

    private final Shooter shooter;

    public Gun(
        String name,
        int damage,
        int speed,
        int cooldown,
        InputManager inputManager,
        Shooter shooter
    ) {
        super(name, damage, speed, cooldown, inputManager);
        this.shooter = shooter;
    }

    @Override
    protected void use(float playerX, float playerY, float mouseX, float mouseY) {
        if (!this.isReady) return;

        float dx = mouseX - playerX;
        float dy = mouseY - playerY;

        float length = (float) Math.sqrt(dx * dx + dy * dy);
        if (length == 0) return;

        float dirX = dx / length;
        float dirY = dy / length;

        float offset = 45f;
        float bulletX = playerX + dirX * offset;
        float bulletY = playerY + dirY * offset;

        this.shooter.shoot(bulletX, bulletY, dirX, dirY, this.damage, this.speed);
        this.resetCooldown();
    }
}
