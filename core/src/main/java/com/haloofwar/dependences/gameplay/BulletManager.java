package com.haloofwar.dependences.gameplay;

import java.util.ArrayList;

import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.entities.Bullet;
import com.haloofwar.enumerators.entities.ProjectileType;
import com.haloofwar.interfaces.BulletListener;

public class BulletManager implements BulletListener {

	private final CollisionManager collision;
	private final EntityManager entities;
	private final TextureManager texture;
	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public BulletManager(CollisionManager collision, EntityManager entities, TextureManager texture) {
		this.collision = collision;
		this.entities = entities;
		this.texture = texture;
	}
	
    public Bullet create(float x, float y, float dirX, float dirY, int damage, int speed) {
        Bullet bullet = new Bullet(x, y, dirX, dirY, damage, speed, texture.get(ProjectileType.BULLET), this);

        this.bullets.add(bullet);
        this.collision.add(bullet);
        this.entities.add(bullet);

        return bullet;
    }
	
	@Override
	public void onBulletDestroyed(Bullet bullet) {
		this.bullets.remove(bullet);
		this.collision.remove(bullet);
		this.entities.remove(bullet);
	}

}
