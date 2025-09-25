package com.haloofwar.engine.systems;

import com.haloofwar.common.enums.GameState;
import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.game.components.MovementComponent;
import com.haloofwar.interfaces.Updatable;

public class MovementSystem extends BaseSystem implements Updatable {

	private boolean canMove = true;

    public MovementSystem(EventBus bus) {
        bus.subscribe(GameStateEvent.class, this::onGameState);
    }
	
    @Override
    public void register(Entity e) {
        if (e.hasComponent(MovementComponent.class) && e.hasComponent(TransformComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {    
        if (!this.canMove) {
        	return;
        }
    	
        for (Entity entity : this.ENTITIES) {
            MovementComponent movement = entity.getComponent(MovementComponent.class);
            TransformComponent transform = entity.getComponent(TransformComponent.class);
            
            if (movement == null || transform == null) {
            	continue;
            }
            
            movement.controller.update(delta);
            
            movement.lastX = transform.x;
            movement.lastY = transform.y;
            
            float dirX = movement.controller.getDirectionX();
            float dirY = movement.controller.getDirectionY();

            transform.x += dirX * movement.speed * delta;
            transform.y += dirY * movement.speed * delta;
        }
    }
    
    private void onGameState(GameStateEvent event) {
        this.canMove = (event.getState() == GameState.PLAYING);
    }
}
