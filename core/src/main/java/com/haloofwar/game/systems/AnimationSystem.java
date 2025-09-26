package com.haloofwar.game.systems;

import com.haloofwar.common.enums.GameState;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.engine.systems.EventSystem;
import com.haloofwar.game.components.AnimationComponent;
import com.haloofwar.game.components.MovementComponent;
import com.haloofwar.interfaces.Updatable;

public class AnimationSystem extends EventSystem implements Updatable {

	private boolean canMove = true;
	
	public AnimationSystem(EventBus bus) {
		this.listenerManager.add(bus, GameStateEvent.class, this::onGameState);
	}
	
    @Override
    public void register(Entity e) {
        if (e.hasComponent(AnimationComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
        for (Entity entity : this.ENTITIES) {
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
