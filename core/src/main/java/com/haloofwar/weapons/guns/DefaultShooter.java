package com.haloofwar.weapons.guns;

import com.haloofwar.dependences.audio.SoundManager;
import com.haloofwar.dependences.gameplay.BulletManager;
import com.haloofwar.enumerators.game.SoundType;
import com.haloofwar.interfaces.Shooter;

public class DefaultShooter implements Shooter {

    private final BulletManager bulletManager;
    private final SoundManager soundManager;

    public DefaultShooter(BulletManager bulletManager, SoundManager soundManager) {
        this.bulletManager = bulletManager;
        this.soundManager = soundManager;
    }

    @Override
    public void shoot(float x, float y, float dirX, float dirY, int damage, int speed) {
        this.bulletManager.create(x, y, dirX, dirY, damage, speed);
        this.soundManager.play(SoundType.SHOOT_ASSAULT_RIFLE);
    }
}
