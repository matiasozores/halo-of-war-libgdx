package com.haloofwar.game.factories;

import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.context.GameplayContext;
import com.haloofwar.common.enumerators.EnemyType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.components.TargetComponent;
import com.haloofwar.game.components.TransformComponent;
import com.haloofwar.game.components.VisibilityComponent;
import com.haloofwar.game.config.ComponentPresets;

public final class EnemyFactory {
    private final TextureManager TEXTURE;
    private final GameplayContext GAMEPLAY;
    
    public EnemyFactory(final GameContext CONTEXT) {
        this.TEXTURE = CONTEXT.getTEXTURE();
        this.GAMEPLAY = CONTEXT.getGAMEPLAY();
    }

    public Entity create(final int IDENTIFIER, final EnemyType TYPE, final float X, final float Y) {
        if(this.GAMEPLAY.getCurrentPlayer() == null) {
        	System.out.println("No se puede crear al enemigo, el jugador es nulo y no tiene trackeo... | EnemyFactory");
        	return null;
        }
    	
        Entity player = this.GAMEPLAY.generateTarget();
        
        if(player == null) {
        	System.out.println("Ha ocurrido un error al intentar generar a un enemigo, no existe un target disponible...");
        }
        
    	final Entity ENTITY = new Entity();
        TransformComponent transform = ComponentPresets.defaultTransform(IDENTIFIER, X, Y);
        ENTITY.addComponent(transform);
        ENTITY.addComponent(ComponentPresets.defaultAnimation(TYPE, this.TEXTURE));
        ENTITY.addComponent(ComponentPresets.defaultHealth());
        ENTITY.addComponent(ComponentPresets.defaultCollision(X, Y));
        ENTITY.addComponent(ComponentPresets.defaultName(TYPE));
        ENTITY.addComponent(ComponentPresets.defaultEnemy(TYPE));
        ENTITY.addComponent(ComponentPresets.defaultObstacle());
        ENTITY.addComponent(ComponentPresets.enemyMovement(transform, player.getComponent(TransformComponent.class), player.getComponent(VisibilityComponent.class)));
        
        if(TYPE.getWeapon() != null) {
        	 ENTITY.addComponent(ComponentPresets.defaultEquipment(TYPE.getWeapon()));
             ENTITY.addComponent(ComponentPresets.enemyWeaponAI(TYPE.getMinShootTimer(), TYPE.getMaxShootTimer()));
        }
       
        ENTITY.addComponent(new TargetComponent(player.getComponent(TransformComponent.class))); 
        
        return ENTITY;
    }

}