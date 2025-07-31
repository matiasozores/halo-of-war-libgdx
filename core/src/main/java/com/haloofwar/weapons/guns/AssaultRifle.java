package com.haloofwar.weapons.guns;

import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.collision.CollisionManager;
import com.haloofwar.dependences.gameplay.BulletManager;
import com.haloofwar.dependences.input.InputManager;

public class AssaultRifle extends Gun{
	public AssaultRifle(InputManager input, BulletManager bullet, TextureManager texture, CollisionManager collision) {
		super("Rifle de Asalto", 10, 50, 20, input, bullet, texture, collision);
	}
}
