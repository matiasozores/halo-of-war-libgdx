package com.haloofwar.ecs.components.gameplay;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.ecs.components.Component;

public class BulletComponent implements Component {
    public float dirX, dirY;
    public float speed;
    public int damage;
    public boolean active = true;
    public Texture texture;

    public BulletComponent(float dirX, float dirY, int speed, int damage, Texture texture) {
        this.dirX = dirX;
        this.dirY = dirY;
        this.speed = speed;
        this.damage = damage;
        this.texture = texture;
    }
}
