package com.haloofwar.ecs.systems.physics;

import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.gameplay.GameplayContext;
import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.collision.CollisionComponent;
import com.haloofwar.ecs.components.gameplay.BulletComponent;
import com.haloofwar.ecs.components.physics.TransformComponent;
import com.haloofwar.ecs.components.render.RenderComponent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.ShootBulletEvent;
import com.haloofwar.ecs.systems.BaseSystem;
import com.haloofwar.enumerators.entities.ProjectileType;

public class BulletSystem extends BaseSystem {

	private static final int SPEED_MULTIPLIER = 10;
	private final TextureManager texture;
	private final GameplayContext context;
    
    public BulletSystem(TextureManager texture, GameplayContext context, EventBus bus) {
		this.texture = texture;
		this.context = context;
		bus.subscribe(ShootBulletEvent.class, this::spawnBullet);
	}
    
    @Override
    public void register(Entity e) {
        if(e.hasComponent(BulletComponent.class) && e.hasComponent(RenderComponent.class)) {
        	super.register(e);
        }
    }

    // Por ahora depende de esta clase la creacion de balas pero a futuro cambiara porque no estoy seguro de
    // que sea la responsabilidad del sistema sino de una fabrica en base a como vinimos armando el proyecto
    // ademas que hay que tratarlas como una entidad 
	private void spawnBullet(ShootBulletEvent event) {
        Entity bullet = new Entity();

        BulletComponent bulletComp = new BulletComponent(
            event.dirX, event.dirY, event.speed * SPEED_MULTIPLIER, event.damage
        );
        
        bullet.addComponent(bulletComp);
        
        TransformComponent transform = new TransformComponent(event.x, event.y, 16, 16);
        bullet.addComponent(transform);

        float angle = (float) Math.toDegrees(Math.atan2(event.dirY, event.dirX));
        RenderComponent render = new RenderComponent(this.texture.get(ProjectileType.BULLET), angle);
        bullet.addComponent(render);

        bullet.addComponent(new CollisionComponent(transform.width, transform.height));
        
        this.context.addEntity(bullet);
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
            transform.x += bullet.dirX * bullet.speed * delta;
            transform.y += bullet.dirY * bullet.speed * delta;
        }
    }

    public void destroy(Entity entity) {
        if (entity.hasComponent(BulletComponent.class)) {
            entity.getComponent(BulletComponent.class).active = false;
        }
    }
    
}
