package com.haloofwar.engine.events.online;

import com.haloofwar.common.enumerators.BulletType;

public class ShootBulletEventOnline {
	public final int identifier;
    public final float x;
    public final float y;
    public final float dirX;
    public final float dirY;
    public final float speed;
    public final int damage;
    public final BulletType type;
    
    public ShootBulletEventOnline(final int identifier, float x, float y, float dirX, float dirY, int damage, float speed, BulletType type) {
        this.identifier = identifier;
    	this.x = x;
        this.y = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.damage = damage;
        this.speed = speed;
        this.type = type;
    }
}
