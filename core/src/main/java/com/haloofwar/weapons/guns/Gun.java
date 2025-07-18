package com.haloofwar.weapons.guns;

import java.util.ArrayList;

import com.haloofwar.utilities.Resources;
import com.haloofwar.weapons.Weapon;

public abstract class Gun extends Weapon{

	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private int bulletSpeed;
	
	public Gun(int bulletSpeed) {
		super("Rifle de Asalto", 10, 60);
		this.bulletSpeed = bulletSpeed;
	}

	@Override
	public void use(int playerX, int playerY) {
		if (this.isReady) {
			if(this.inputManager.isAttack()) {
				this.resetCooldown();
				Bullet bullet = new Bullet(playerX, playerY, Resources.getMouseX(), Resources.getMouseY(), this.damage, this.bulletSpeed);
				this.bullets.add(bullet);
			}
			
		} 
	}
	
	@Override 
	public void update() {
		super.update();
		
		for (int i = 0; i < this.bullets.size(); i++) {
			this.bullets.get(i).update();
			
			if(!this.bullets.get(i).isActive()) {
				this.bullets.remove(i);
			}
		}
	}
	
	@Override
	public void render() {

		for (Bullet bullet : this.bullets) {
			bullet.render();
		}
	}
	
}
