package com.haloofwar.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.collision.behaviors.BulletCollisionBehavior;
import com.haloofwar.interfaces.Collidable;
import com.haloofwar.interfaces.CollisionVisitor;
import com.haloofwar.interfaces.StateHandler;
import com.haloofwar.interfaces.Updatable;

public class Bullet extends Entity implements Updatable {

    private final int DEFAULT_SPEED_MULTIPLIER = 10;

    private float dirX, dirY;
    private float speed;
    private boolean active;
    private int damage;

    private final Texture texture;

    public Bullet(
        float x, float y,
        float dirX, float dirY,
        int damage, int speed,
        Texture texture,
        StateHandler state
    ) {
        super("Bullet", x, y, 16, 16, state);
        this.dirX = dirX;
        this.dirY = dirY;
        this.speed = speed;
        this.damage = damage;
        this.texture = texture;
        this.active = true;
        this.collisionBehavior = new BulletCollisionBehavior();
    }

    @Override
    public void update(float delta) {
        if (!this.active) {
        	return;
        }

        this.x += (this.dirX * this.speed * delta) * this.DEFAULT_SPEED_MULTIPLIER;
        this.y += (this.dirY * this.speed * delta) * this.DEFAULT_SPEED_MULTIPLIER;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (!this.active) {
        	return;
        }

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
        this.state.onDeath(this);
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    @Override
    public void accept(CollisionVisitor visitor, Collidable self) {
        visitor.visit(this, self);
    }
}
