package com.haloofwar.events;

public class ShootBulletEvent {
    public final float x;
    public final float y;
    public final float dirX;
    public final float dirY;
    public final float speed;
    public final int damage;

    public ShootBulletEvent(float x, float y, float dirX, float dirY, int damage, float speed) {
        this.x = x;
        this.y = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.damage = damage;
        this.speed = speed;
    }
}
