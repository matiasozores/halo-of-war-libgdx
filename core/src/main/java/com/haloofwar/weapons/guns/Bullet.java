package com.haloofwar.weapons.guns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.collision.Collidable;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.enumerators.entities.ProjectileType;
import com.haloofwar.enumerators.entities.behavior.CollisionType;

public class Bullet implements Collidable{
	private final int SPEED_MULTIPLIER = 10;
	
    private float positionX, positionY;
    private float dirX, dirY;
    private float speed = 500;
    private boolean active = true;
    private int damage;
    private int width = 16, height = 16; 
    
    private Texture texture;
    
    public Bullet(float x, float y, float dirX, float dirY, int damage, int speed, TextureManager texture, CollisionManager collision) {
        this.positionX = x;
        this.positionY = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.damage = damage;
        this.speed = speed;
        this.texture = texture.get(ProjectileType.BULLET);
        
        collision.addCollidable(this);
    }

    public void update(float delta, int mapWidth, int mapHeight) {
        if (!this.active) {
        	return;
        }

        this.positionX += (this.dirX * this.speed * delta) * this.SPEED_MULTIPLIER;
        this.positionY += (this.dirY * this.speed * delta) * this.SPEED_MULTIPLIER;
        
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
        float angle = (float) Math.toDegrees(Math.atan2(dirY, dirX));

       
        // Generado con CHATGPT (para direccion de la bala)
        batch.draw(
        	    texture,
        	    positionX, positionY,            
        	    width / 2f, height / 2f,       
        	    width, height,               
        	    1f, 1f,                 
        	    angle,                  
        	    0, 0,                          
        	    texture.getWidth(), texture.getHeight(),
        	    false, false                      
        	);

    }

    public boolean isActive() {
        return this.active;
    }
    
    public void destroy() {
    	this.active = false;
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
