package com.haloofwar.dependences;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.weapons.guns.Bullet;

public class BulletManager {
	private final ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	public void add(Bullet bullet) {
		this.bullets.add(bullet);
	}
	
	public void update(float delta) {
    	if(this.bullets.size() > 0) {
    		for (int i = this.bullets.size() - 1; i >= 0; i--) {
				this.bullets.get(i).update(delta, 2000, 2000);
				if(!this.bullets.get(i).isActive()) {
					this.bullets.remove(i);
				}
			}
    	}
	}
	
	public void render(SpriteBatch batch) {
		for(Bullet bullet : this.bullets) {
			bullet.render(batch);
		}
	}
	
	public void clear() {
		this.bullets.clear();
	}
	
	public ArrayList<Bullet> getBullets(){
		return this.bullets;
	}
}
