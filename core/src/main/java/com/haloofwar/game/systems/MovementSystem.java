package com.haloofwar.game.systems;

import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.ChangeTargetEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.SetCanMoveEvent;
import com.haloofwar.engine.events.online.MoveEventOnline;
import com.haloofwar.engine.events.online.UpdateEntityPositionEventOnline;
import com.haloofwar.game.components.EnemyComponent;
import com.haloofwar.game.components.MovementComponent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.components.TransformComponent;
import com.haloofwar.interfaces.Updatable;

public class MovementSystem extends EventSystem implements Updatable {

    public MovementSystem(EventBus bus) {
    	super(bus);
    	this.listenerManager.add(bus, MoveEventOnline.class, this::onMove);
    	this.listenerManager.add(bus, SetCanMoveEvent.class, this::onCanMove);
    	this.listenerManager.add(bus, ChangeTargetEvent.class, this::onChangeTarget);
    }
    
    private void onChangeTarget(ChangeTargetEvent event) {
    	for (Entity entity : this.ENTITIES) {
			if(entity.hasComponent(EnemyComponent.class)) {
				MovementComponent mc = entity.getComponent(MovementComponent.class);
				mc.changeTarget(event.newTarget);
			}
		}
    }
	
    @Override
    public void register(Entity e) {
        if (e.hasComponent(MovementComponent.class) && e.hasComponent(TransformComponent.class)) {
            super.register(e);
        }
    }
    
    private void onCanMove(SetCanMoveEvent event) {
    	Entity target = this.getEntityByPlayerType(event.playerType);
    	MovementComponent mc = target.getComponent(MovementComponent.class);
    	mc.controller.setMove(event.canMove);
    }
    
    private void onMove(MoveEventOnline event) {
		Entity entity = this.getEntityByIdentifier(event.IDENTIFIER);	
		
		if(entity == null) {
			return;
		}
		
		MovementComponent movement = entity.getComponent(MovementComponent.class);
		movement.controller.onUpdatePosition(event.direction, event.pressed);
	}

    @Override
    public void update(float delta) {        	
        this.updateAllEntitiesForClients(delta);
    }
    
    private void updateAllEntitiesForClients(float delta) {
    	for(Entity entity : this.ENTITIES) {
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
            
            final int IDENTIFIER = this.getIdentifier(entity);
            
            if(IDENTIFIER == -1) {
            	return;
            }
            
            this.bus.publish(new UpdateEntityPositionEventOnline(IDENTIFIER, transform.x, transform.y, movement.controller.getDirectionX(), movement.controller.getDirectionY()));
    	}
    }
    
    private int getIdentifier(Entity entity) {
    	if(!entity.hasComponent(TransformComponent.class)) {
    		System.out.println("Ha ocurrido un error al obtener el identificador de una entidad | MovementSystem");
    		return -1;
    	}
    	
    	return entity.getComponent(TransformComponent.class).identifier;
    }
    
    private Entity getEntityByIdentifier(final int IDENTIFIER) {
    	boolean found = false;
    	int i = 0;
    	Entity entity = null;
    	
    	while(!found && i < this.ENTITIES.size()) {
    		Entity current = this.ENTITIES.get(i);
    		
    		TransformComponent tc = current.getComponent(TransformComponent.class);
    		if(tc.identifier == IDENTIFIER) {
    			found = true;
    			entity = current;
    		} else {
    			i++;
    		}
    	}
    	
    	return entity;
    }
    
    private Entity getEntityByPlayerType(PlayerType type) {
    	boolean found = false;
    	int i = 0;
    	Entity entity = null;
    	
    	while(!found && i < this.ENTITIES.size()) {
    		Entity current = this.ENTITIES.get(i);
    		
    		if(current.hasComponent(PlayerComponent.class)) {
    			PlayerComponent pc = current.getComponent(PlayerComponent.class);
    			
    			if(pc.type.getName().equals(type.getName())) {
    				found = true;
    				entity = current;
    			} else {
    				i++;
    			}
    		} else {
    			i++;
    		}
    	}
    	
    	return entity;
    }

}
