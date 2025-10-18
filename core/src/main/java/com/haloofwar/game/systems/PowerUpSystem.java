package com.haloofwar.game.systems;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PowerUpCollectedEvent;
import com.haloofwar.game.components.HealthComponent;
import com.haloofwar.game.components.PowerUpComponent;
import com.haloofwar.game.components.VisibilityComponent;

public class PowerUpSystem extends EventSystem {
	
    public PowerUpSystem(EventBus bus) {
    	super(bus);
        this.listenerManager.add(bus, PowerUpCollectedEvent.class, this::onPowerUpCollected);
    }

    private void onPowerUpCollected(PowerUpCollectedEvent event) {
       Entity player = event.player;
       PowerUpComponent powerUpComp = event.powerUp.getComponent(PowerUpComponent.class);
       
       switch (powerUpComp.type) {
		case SOBRE_ESCUDO:
			HealthComponent healthComp = player.getComponent(HealthComponent.class);
			healthComp.setShield(powerUpComp.amount);
			break;
			
		case INVISIBILIDAD:
			VisibilityComponent visibilityComp = player.getComponent(VisibilityComponent.class);
			visibilityComp.setInvisible(powerUpComp.duration);
			break;
	
		default:
			System.out.println("Ha ocurrido un error al recoger el power up");
			break;
		}
    }
}
