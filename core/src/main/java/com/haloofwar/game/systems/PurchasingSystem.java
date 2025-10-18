package com.haloofwar.game.systems;

import com.haloofwar.common.context.GameplayContext;
import com.haloofwar.common.enumerators.ObjectType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PurchaseWeaponEvent;
import com.haloofwar.engine.events.online.BuyWeaponEventOnline;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.InventoryComponent;
import com.haloofwar.game.factories.WeaponFactory;

public class PurchasingSystem {
	private GameplayContext context;
	private EventBus gameplayBus;

	public PurchasingSystem(GameplayContext context) {
		this.context = context;
		this.gameplayBus = context.getBus();
		this.gameplayBus.subscribe(PurchaseWeaponEvent.class, this::purchaseWeapon);
	}

	private void purchaseWeapon(PurchaseWeaponEvent event) {
		Entity player = this.context.getPlayerByType(event.playerType);
		if (player == null) {
			System.out.println("Ha ocurrido un error al comprar el arma: jugador no encontrado | PurchasingSystem");
			return;
		}
		
		InventoryComponent inventory = player.getComponent(InventoryComponent.class);
		final int MONEY = inventory.getItemCount(ObjectType.MONEDA_DE_ORO);
		final int PRICE = event.weapon.getPrice();
		
		if(MONEY < PRICE) {
			System.out.println("No tienes suficiente dinero para comprar este arma.");
			return;
		}
		
		final boolean REMOVED = inventory.remove(ObjectType.MONEDA_DE_ORO, PRICE);
		
		if(!REMOVED) {
			System.out.println("Ha ocurrido un error al comprar el arma: no se ha podido quitar el dinero | PurchasingSystem");
			return;
		}
		
        final Entity newWeapon = WeaponFactory.createWeapon(event.weapon);
        
        final EquipmentComponent equipment = player.getComponent(EquipmentComponent.class);
        
        equipment.weaponInventory.add(newWeapon);
        
        
        this.gameplayBus.publish(new BuyWeaponEventOnline(event.weapon, event.playerType, event.weapon.getPrice()));
	}
}
