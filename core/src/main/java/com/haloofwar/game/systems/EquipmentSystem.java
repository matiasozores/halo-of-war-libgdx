package com.haloofwar.game.systems;

import com.haloofwar.common.context.GameplayContext;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.ChangeCurrentWeaponEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.online.ChangeWeaponEventOnline;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.interfaces.Weapon;

public class EquipmentSystem {
	private GameplayContext context;
	private EventBus gameplayBus;
	
	public EquipmentSystem(GameplayContext context, EventBus gameplayBus) {
		this.context = context;
		this.gameplayBus = gameplayBus;
		this.gameplayBus.subscribe(ChangeCurrentWeaponEvent.class, this::onChangeCurrentWeapon);
	}
	
	private void onChangeCurrentWeapon(ChangeCurrentWeaponEvent event) {
		Entity player = this.context.getPlayerByType(event.playerType);
		
		if(player == null) {
			System.out.println("Ha ocurrido un problema al cambiar el arma a un jugador...");
			return;
		}
		
		EquipmentComponent equipment = player.getComponent(EquipmentComponent.class);
		
		Weapon currentWeapon = equipment.getCurrentWeapon();
		
		if(currentWeapon.getName().equals(event.weapon.getName())) {
			System.out.println("Ya tienes equipado el arma que has seleccionado!");
			return;
		} 
		
		Entity newCurrentWeapon = equipment.getEntityByWeapon(event.weapon);
		
		if(newCurrentWeapon == null) {
			System.out.println("Se ha intentado seleccionar un arma que no existe en el inventario...");
			return;
		}
		
		equipment.currentWeapon = newCurrentWeapon;
		
		this.gameplayBus.publish(new ChangeWeaponEventOnline(event.playerType, event.weapon));
	}
	
}
