package com.haloofwar.game.systems;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.common.context.GameplayContext;
import com.haloofwar.common.enumerators.InventoryItemStatus;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.AddWeaponToInventoryEvent;
import com.haloofwar.engine.events.AttackEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.InteractEvent;
import com.haloofwar.engine.events.UpdateCurrentWeaponEvent;
import com.haloofwar.engine.events.online.InteractEventOnline;
import com.haloofwar.engine.events.online.InventoryUpdateEventOnline;
import com.haloofwar.game.components.CrosshairComponent;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.FireArmComponent;
import com.haloofwar.game.components.InventoryComponent;
import com.haloofwar.game.components.MeleeWeaponComponent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.components.TransformComponent;
import com.haloofwar.game.factories.ObjectFactory;
import com.haloofwar.game.factories.WeaponFactory;
import com.haloofwar.interfaces.Weapon;

public class PlayerSystem extends EventSystem {
	private EventBus bus;
	private TextureManager texture;
	private GameplayContext context;
	private Entity player;
	
	public PlayerSystem(Entity player, EventBus bus, TextureManager texture, GameplayContext context) {
		this.listenerManager.add(bus, InteractEvent.class, this::onInteract);
		this.listenerManager.add(bus, InventoryUpdateEventOnline.class, this::onUpdateInventory);
		this.listenerManager.add(bus, AddWeaponToInventoryEvent.class, this::onUpdateWeaponInventory);
		this.listenerManager.add(bus, UpdateCurrentWeaponEvent.class, this::onUpdateCurrentWeapon);
		this.listenerManager.add(bus, AttackEvent.class, this::onAttack);
		this.bus = bus;
		this.texture = texture;
		this.player = player;
		this.context = context;
	}
	
	private void onInteract(InteractEvent event) {
		if(this.player != null) {
			PlayerComponent pc = this.player.getComponent(PlayerComponent.class);
			this.bus.publish(new InteractEventOnline(pc.type, event.isPressed()));
			pc.isInteracting = event.isPressed(); // para local
		}
	}
	
	private void onUpdateInventory(InventoryUpdateEventOnline event) {		
		PlayerType playerType = this.player.getComponent(PlayerComponent.class).type;
		
		if(playerType.equals(event.playerType)) {
			InventoryComponent inventory = this.player.getComponent(InventoryComponent.class);
			
			if(event.status.equals(InventoryItemStatus.REMOVE)) {
				inventory.remove(event.itemType, event.quantity);
			} else if(event.status.equals(InventoryItemStatus.ADD)) {
				Entity entity = ObjectFactory.createItem(event.identifier, new Rectangle(0,0,0,0), event.itemType, this.texture);
				inventory.add(entity, event.quantity);
			} else {
				System.out.println("Status de item de inventario no reconocido: " + event.status);
			}
		}	
	}
	
	private void onUpdateWeaponInventory(AddWeaponToInventoryEvent event) {
		PlayerType playerType = this.player.getComponent(PlayerComponent.class).type;
		
		if(playerType.equals(event.playerType)) {
			EquipmentComponent equipment = this.player.getComponent(EquipmentComponent.class);
			
			if(equipment.isInInventory(event.weapon.getName())){
				System.out.println("El jugador ya tiene este arma en su inventario: " + event.weapon.getName());
				return;
			}
			
			Entity weaponEntity = WeaponFactory.createWeapon(event.weapon);
			
			equipment.weaponInventory.add(weaponEntity);
			System.out.println("Arma agregada al inventario del jugador: " + event.weapon.getName());
		}
	}
	
	private void onUpdateCurrentWeapon(UpdateCurrentWeaponEvent event) {
		PlayerType playerType = this.player.getComponent(PlayerComponent.class).type;
		
		if(playerType.equals(event.playerType)) {
			EquipmentComponent equipment = this.player.getComponent(EquipmentComponent.class);
			
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
		}
	}
	
	private void onAttack(AttackEvent event) {
	    if (this.player != this.context.getCurrentPlayer()) {
	        return;
	    }

	    EquipmentComponent equipment = this.player.getComponent(EquipmentComponent.class);
	    
	    if (equipment == null) {
	    	return;
	    }

	    Entity weaponEntity = equipment.getEntityCurrentWeapon();
	    
	    if (weaponEntity == null) {
	    	return;
	    }

	    if (weaponEntity.hasComponent(FireArmComponent.class)) {
	        FireArmComponent firearm = weaponEntity.getComponent(FireArmComponent.class);
	        CrosshairComponent crosshair = this.player.getComponent(CrosshairComponent.class);
	        TransformComponent transform = this.player.getComponent(TransformComponent.class);

	        float dx = crosshair.mouseX - (transform.x + transform.width / 2);
	        float dy = crosshair.mouseY - (transform.y + transform.height / 2);
	        float length = (float) Math.sqrt(dx * dx + dy * dy);
	        if (length > 0) {
	            firearm.dirX = dx / length;
	            firearm.dirY = dy / length;
	        }

	        firearm.wantsToFire = event.isPressed();

	    } else if (weaponEntity.hasComponent(MeleeWeaponComponent.class)) {
	        MeleeWeaponComponent melee = weaponEntity.getComponent(MeleeWeaponComponent.class);
	        melee.wantsToSwing = event.isPressed();
	    }
	}
}
