package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.BulletType;

public class ShootBulletEvent {
	
	public final int identifier;
    public final float x;
    public final float y;
    public final float dirX;
    public final float dirY;
    public final float speed;
    public final int damage;
    public final BulletType type;
    
    public ShootBulletEvent(final int identifier, float x, float y, float dirX, float dirY, int damage, float speed, BulletType type) {
        this.x = x;
        this.y = y;
        this.dirX = dirX;
        this.dirY = dirY;
        this.damage = damage;
        this.speed = speed;
        this.type = type;
        this.identifier = identifier;
    }
}
