package com.haloofwar.systems;

import com.haloofwar.components.Entity;
import com.haloofwar.components.HealthComponent;
import com.haloofwar.components.PowerUpComponent;
import com.haloofwar.components.VisibilityComponent;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.PowerUpCollectedEvent;
import com.haloofwar.interfaces.Registrable;

public class PowerUpSystem implements Registrable {
    private final EventBus bus;

    public PowerUpSystem(EventBus bus) {
        this.bus = bus;
        this.bus.subscribe(PowerUpCollectedEvent.class, this::onPowerUpCollected);
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

    @Override
    public void register(Entity entity) {}
    @Override
    public void unregister(Entity entity) {}
}
