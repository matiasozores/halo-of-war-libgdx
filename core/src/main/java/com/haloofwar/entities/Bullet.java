package com.haloofwar.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.enumerators.entities.behavior.CollisionType;
import com.haloofwar.interfaces.BulletListener;
import com.haloofwar.interfaces.Updatable;

public class Bullet extends Entity implements Updatable {

    private static final int SPEED_MULTIPLIER = 10;

    private float dirX, dirY;
    private float speed;
    private boolean active;
    private int damage;

    private final Texture texture;
    private final BulletListener listener;

    public Bullet(
        float x, float y,
        float dirX, float dirY,
        int damage, int speed,
        Texture texture,
        BulletListener listener
    ) {
        super("Bullet", x, y, 16, 16, CollisionType.BULLET);
        this.dirX = dirX;
        this.dirY = dirY;
        this.speed = speed;
        this.damage = damage;
        this.texture = texture;
        this.active = true;
        this.listener = listener;
        
    }

    @Override
    public void update(float delta) {
        if (!active) return;

        this.x += (dirX * speed * delta) * SPEED_MULTIPLIER;
        this.y += (dirY * speed * delta) * SPEED_MULTIPLIER;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (!active) return;

        // Calculo hecho con CHATPGT --------
        
        float angle = (float) Math.toDegrees(Math.atan2(dirY, dirX));

        batch.draw(
            this.texture,
            this.x, this.y,
            this.width / 2f, this.height / 2f,
            this.width, this.height,
            1f, 1f,
            angle,
            0, 0,
            this.texture.getWidth(), this.texture.getHeight(),
            false, false
        );
        
        // ----------------------------------
    }

    public boolean isActive() {
        return active;
    }

    public void destroy() {
        if (!active) return;
        this.active = false;
        if (listener != null) {
            listener.onBulletDestroyed(this);
        }
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
}
