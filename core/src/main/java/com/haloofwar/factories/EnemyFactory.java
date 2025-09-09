package com.haloofwar.factories;

import com.haloofwar.components.Entity;
import com.haloofwar.components.TargetComponent;
import com.haloofwar.components.TransformComponent;
import com.haloofwar.components.VisibilityComponent;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.EnemyType;
import com.haloofwar.factories.components.ComponentPresets;
import com.haloofwar.interfaces.EntityDescriptor;
import com.haloofwar.interfaces.EntityFactory;

public final class EnemyFactory implements EntityFactory {

    private final GameContext context;

    public EnemyFactory(GameContext context) {
        this.context = context;
    }

    @Override
    public Entity create(EntityDescriptor type, float x, float y) {
        if (!(type instanceof EnemyType enemyType)) {
            throw new IllegalArgumentException("Tipo inválido para PlayerFactory");
        }

        Entity entity = new Entity();
        TransformComponent transform = ComponentPresets.defaultTransform(x, y);
        entity.addComponent(transform);
        entity.addComponent(ComponentPresets.defaultAnimation(enemyType, this.context.getTexture()));
        entity.addComponent(ComponentPresets.defaultHealth());
        entity.addComponent(ComponentPresets.defaultCollision(x, y));
        entity.addComponent(ComponentPresets.defaultName(enemyType));
        entity.addComponent(ComponentPresets.defaultEnemy());
        entity.addComponent(ComponentPresets.defaultObstacle());
        entity.addComponent(ComponentPresets.enemyMovement(transform, this.context.getGameplay().getPlayer().getComponent(TransformComponent.class), this.context.getGameplay().getPlayer().getComponent(VisibilityComponent.class)));
        entity.addComponent(ComponentPresets.defaultWeapon(enemyType.getWeapon()));
        entity.addComponent(ComponentPresets.enemyWeaponAI());
        entity.addComponent(new TargetComponent(this.context.getGameplay().getPlayer().getComponent(TransformComponent.class))); 
        
        return entity;
    }

}