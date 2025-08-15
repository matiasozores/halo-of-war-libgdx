package com.haloofwar.ecs.systems.physics;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.gameplay.GameplayContext;
import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.collision.CollisionComponent;
import com.haloofwar.ecs.components.gameplay.BulletComponent;
import com.haloofwar.ecs.components.physics.TransformComponent;
import com.haloofwar.ecs.components.render.RenderComponent;
import com.haloofwar.ecs.systems.BaseSystem;
import com.haloofwar.enumerators.entities.ProjectileType;

public class BulletSystem extends BaseSystem {

	private final TextureManager texture;
	private final GameplayContext context;
    private static final float SPEED_MULTIPLIER = 10f;
    
    public BulletSystem(TextureManager texture, GameplayContext context) {
		this.texture = texture;
		this.context = context;
	}
    
    @Override
    public void register(Entity e) {
        if(e.hasComponent(BulletComponent.class) && e.hasComponent(RenderComponent.class)) {
        	super.register(e);
        }
    }

	public Entity spawnBullet(float x, float y, float dirX, float dirY, int damage, int speed) {
        Entity bullet = new Entity();

        BulletComponent bulletComp = new BulletComponent(
            dirX, dirY, speed, damage,
            texture.get(ProjectileType.BULLET)
        );
        
        bullet.addComponent(bulletComp);
        
        TransformComponent transform = new TransformComponent(x, y, 16, 16);
        bullet.addComponent(transform);

        float angle = (float) Math.toDegrees(Math.atan2(dirY, dirX));
        RenderComponent render = new RenderComponent(bulletComp.texture, angle);
        bullet.addComponent(render);

        bullet.addComponent(new CollisionComponent(new Rectangle(transform.x, transform.y, transform.width, transform.height)));
        
        this.context.addEntity(bullet);
        
        return bullet;
    }

    
    @Override
    public void update(float delta) {
        for (Entity entity : this.entities) {
            BulletComponent bullet = entity.getComponent(BulletComponent.class);
            TransformComponent transform = entity.getComponent(TransformComponent.class);
            if (!bullet.active) {
            	continue;
            }

            // Solo mueve la bala
            transform.x += bullet.dirX * bullet.speed * delta * SPEED_MULTIPLIER;
            transform.y += bullet.dirY * bullet.speed * delta * SPEED_MULTIPLIER;
        }
    }

    public void destroy(Entity entity) {
        if (entity.hasComponent(BulletComponent.class)) {
            entity.getComponent(BulletComponent.class).active = false;
        }
    }
    
    
}
