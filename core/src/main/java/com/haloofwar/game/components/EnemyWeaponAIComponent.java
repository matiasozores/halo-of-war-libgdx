package com.haloofwar.game.components;

import com.badlogic.gdx.math.MathUtils;

public class EnemyWeaponAIComponent implements Component {
    public float shootTimer;
    public float shootInterval;

    public EnemyWeaponAIComponent(float minShootInterval, float maxShootInterval) {
        this.shootTimer = MathUtils.random(0f, maxShootInterval);
        this.shootInterval = MathUtils.random(minShootInterval, maxShootInterval);
    }
}