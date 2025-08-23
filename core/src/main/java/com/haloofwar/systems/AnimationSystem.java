package com.haloofwar.systems;

import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.Entity;
import com.haloofwar.components.MovementComponent;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.GameStateEvent;
import com.haloofwar.interfaces.Updatable;

public class AnimationSystem extends BaseSystem implements Updatable {

	private boolean canMove = true;
	
	public AnimationSystem(EventBus bus) {
		bus.subscribe(GameStateEvent.class, this::onGameState);
	}
	
    @Override
    public void register(Entity e) {
        if (e.hasComponent(AnimationComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
 
        for (Entity entity : this.entities) {
            AnimationComponent animation = entity.getComponent(AnimationComponent.class);
            if (animation == null) continue;

            MovementComponent movement = entity.getComponent(MovementComponent.class);
            float dirX = 0, dirY = 0;
            if (movement != null) {
                dirX = movement.controller.getDirectionX();
                dirY = movement.controller.getDirectionY();
            }
            
            animation.update(delta, dirX, dirY, this.canMove);
        }
    }
    
    private void onGameState(GameStateEvent event) {
        this.canMove = (event.getState() == GameState.PLAYING);
    }
}
