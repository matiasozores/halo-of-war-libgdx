package com.haloofwar.game.components;

public class BulletComponent implements Component {
    public float dirX, dirY;
    public float speed;
    public int damage;
    public boolean active = true;

    public BulletComponent(float dirX, float dirY, float speed, int damage) {
        this.dirX = dirX;
        this.dirY = dirY;
        this.speed = speed;
        this.damage = damage;
    }
}
