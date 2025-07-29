package com.haloofwar.weapons.guns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.collision.Collidable;
import com.haloofwar.collision.CollisionManager;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.enumerators.CollisionType;
import com.haloofwar.enumerators.ProjectileType;

public class Bullet implements Collidable{
	private final int SPEED_MULTIPLIER = 10;
	
    private float positionX, positionY;
    private float dirX, dirY;
    private float speed = 500;
    private boolean active = true;
    private int damage;
    
    private Texture texture;
    private CollisionManager collisionManager;

    public Bullet(float x, float y, float dirX, float dirY, int damage, int speed, TextureManager textureManager, CollisionManager collisionManager) {
        this.positionX = x;
        this.positionY = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.damage = damage;
        this.speed = speed;
        this.texture = textureManager.get(ProjectileType.BULLET);
        collisionManager.addCollidable(this);
        this.collisionManager = collisionManager;
    }

    public void update(float delta, int mapWidth, int mapHeight) {
        if (!this.active) {
        	return;
        }

        this.positionX += (this.dirX * this.speed * delta) * this.SPEED_MULTIPLIER;
        this.positionY += (this.dirY * this.speed * delta) * this.SPEED_MULTIPLIER;
        
        // En caso de que se pase de los limites se destruye
        if(this.positionX <= 0 || this.positionX >= (float) mapWidth) {
        	this.destroy();
        } else if(this.positionY <= 0 || this.positionY >= (float) mapHeight) {
        	this.destroy();
        }
    }

    public void render(SpriteBatch batch) {
        if (!this.active) {
        	return;
        }
        
        batch.draw(this.texture, this.positionX, this.positionY);
    }

    public boolean isActive() {
        return this.active;
    }
    
    public void destroy() {
    	this.active = false;
    	this.collisionManager.removeCollidable(this);
    }

	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.positionX, this.positionY, 8, 8);
	}

	@Override
	public CollisionType getCollisionType() {
		return CollisionType.BULLET;
	}
	
	public int getDamage() {
	    return this.damage;
	}

}
