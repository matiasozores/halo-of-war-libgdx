package com.haloofwar.systems;

import com.haloofwar.components.Entity;
import com.haloofwar.components.MovementComponent;
import com.haloofwar.components.TransformComponent;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.GameStateEvent;
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
    	
        for (Entity entity : this.entities) {
            MovementComponent movement = entity.getComponent(MovementComponent.class);
            TransformComponent transform = entity.getComponent(TransformComponent.class);

            if (movement == null || transform == null) {
            	continue;
            }
            
            movement.lastX = transform.x;
            movement.lastY = transform.y;
            
            float dirX = movement.controller.getDirectionX();
            float dirY = movement.controller.getDirectionY();

            // Mover
            transform.x += dirX * movement.speed * delta;
            transform.y += dirY * movement.speed * delta;
        }
    }
    
    private void onGameState(GameStateEvent event) {
        this.canMove = (event.getState() == GameState.PLAYING);
    }

}
