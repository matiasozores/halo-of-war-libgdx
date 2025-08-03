package com.haloofwar.weapons.guns;

import com.haloofwar.dependences.audio.SoundManager;
import com.haloofwar.dependences.gameplay.BulletManager;
import com.haloofwar.dependences.input.InputManager;

public class AssaultRifle extends Gun{
	public AssaultRifle(InputManager input, SoundManager sound, BulletManager bullets) {
		super("Rifle de Asalto", 10, 50, 20, input, new DefaultShooter(bullets, sound));
	}
}
