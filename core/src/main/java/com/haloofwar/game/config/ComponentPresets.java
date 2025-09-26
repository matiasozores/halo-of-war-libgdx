package com.haloofwar.game.config;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.common.enums.ObjectType;
import com.haloofwar.common.enums.PlayerType;
import com.haloofwar.common.enums.PowerUpType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.cameras.GameWorldCamera;
import com.haloofwar.engine.components.Component;
import com.haloofwar.engine.components.RenderComponent;
import com.haloofwar.engine.components.TransformComponent;
import com.haloofwar.engine.entity.AnimatedEntityDescriptor;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.entity.EntityDescriptor;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.interfaces.MovementController;
import com.haloofwar.game.components.AnimationComponent;
import com.haloofwar.game.components.CollectComponent;
import com.haloofwar.game.components.CollisionComponent;
import com.haloofwar.game.components.CrosshairComponent;
import com.haloofwar.game.components.DialogueComponent;
import com.haloofwar.game.components.EnemyComponent;
import com.haloofwar.game.components.EnemyMovementController;
import com.haloofwar.game.components.EnemyWeaponAIComponent;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.HealthComponent;
import com.haloofwar.game.components.InventoryComponent;
import com.haloofwar.game.components.MovementComponent;
import com.haloofwar.game.components.NameComponent;
import com.haloofwar.game.components.ObstacleComponent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.components.PlayerMovementController;
import com.haloofwar.game.components.PortalComponent;
import com.haloofwar.game.components.PowerUpComponent;
import com.haloofwar.game.components.PromptComponent;
import com.haloofwar.game.components.ShieldComponent;
import com.haloofwar.game.components.StockComponent;
import com.haloofwar.game.components.VillagerComponent;
import com.haloofwar.game.components.VisibilityComponent;
import com.haloofwar.game.data.EquipmentData;
import com.haloofwar.game.data.InventoryData;
import com.haloofwar.game.factories.ObjectFactory;
import com.haloofwar.game.factories.WeaponFactory;
import com.haloofwar.interfaces.Talkable;
import com.haloofwar.interfaces.Weapon;

public class ComponentPresets {

	private static final int DEFAULT_HEALTH = 100;
	private static final float WIDTH = 32, HEIGHT = 32;
	private static final float DEFAULT_VELOCITY = 150;
	
	public static TransformComponent defaultTransform(float x, float y) {
		return new TransformComponent(x, y, WIDTH, HEIGHT);
	}
	
	public static TransformComponent defaultTransform(float x, float y, float width, float height) {
		return new TransformComponent(x, y, width, height);
	}
	
    public static AnimationComponent defaultAnimation(AnimatedEntityDescriptor descriptor, TextureManager textureManager) {
        return new AnimationComponent(descriptor, textureManager);
    }
    
    public static HealthComponent defaultHealth() {
        return new HealthComponent(DEFAULT_HEALTH);
    }

    public static CollisionComponent defaultCollision(float x, float y) {
        return new CollisionComponent(WIDTH, HEIGHT, x, y);
    }
    
    public static CollisionComponent defaultCollision(float x, float y, float width, float height) {
        return new CollisionComponent(width, height, x, y);
    }
    
    public static MovementComponent playerMovement(EventBus bus) {
        MovementController controller = new PlayerMovementController(bus);
        return new MovementComponent(controller, DEFAULT_VELOCITY);
    }
    
    public static MovementComponent enemyMovement(TransformComponent enemyTransform, TransformComponent playerTransform, VisibilityComponent visibilityComponent) {
        MovementController controller = new EnemyMovementController(enemyTransform, playerTransform, visibilityComponent);
        return new MovementComponent(controller, DEFAULT_VELOCITY * 0.25f);
    }

    public static CrosshairComponent defaultCrosshair(PlayerType type, TextureManager manager, GameWorldCamera camera) {
    	Texture texture = manager.get(type.getCrosshair());
    	return new CrosshairComponent(texture, camera);
    }
    
    public static NameComponent defaultName(EntityDescriptor type) {
    	return new NameComponent(type.getName());
    }
    
    public static InventoryComponent defaultInventory() {
    	return new InventoryComponent();
    }
    
    public static ObstacleComponent defaultObstacle() {
    	return new ObstacleComponent();
    }
    
    public static RenderComponent defaultRender(EntityDescriptor type, TextureManager manager) {
    	Texture texture = manager.get(type);
        return new RenderComponent(texture);
    }

    public static StockComponent defaultPickup(ObjectType type) {
        return new StockComponent(type);
    }
    
    public static PlayerComponent defaultPlayer(PlayerType type) {
    	return new PlayerComponent(type);
    }
    
    public static PromptComponent defaultPrompt(float offset) {
    	return new PromptComponent(offset);
    }
    
    public static VillagerComponent defaultVillager() {
    	return new VillagerComponent();
    }
    
    public static DialogueComponent defualtDialogue(Talkable type, Texture avatar) {
    	return new DialogueComponent(type.getLines(), avatar);
    }
    
    public static PortalComponent defaultPortal(String targetScene) {
		return new PortalComponent(targetScene);
	}
    
    public static EnemyComponent defaultEnemy() {
		return new EnemyComponent();
	}
    
    public static EnemyWeaponAIComponent enemyWeaponAI() {
    	return new EnemyWeaponAIComponent();
    }
    
    public static CollectComponent defaultCollectible() {
		return new CollectComponent();
	}
    
    public static ShieldComponent defaultShield() {
		return new ShieldComponent();
	}
    
    public static ShieldComponent defaultShield(int shieldPoints) {
    	return new ShieldComponent(shieldPoints);
    }	
    
    public static PowerUpComponent defaultPowerUp(PowerUpType type) {
		return new PowerUpComponent(type, type.getAmount(), type.getDuration());
	}
    
    public static VisibilityComponent defaultVisibility() {
		return new VisibilityComponent();
	}
    
    public static Component defaultWeapon(Weapon weapon) {
		return weapon.createComponent();
	}

    public static EquipmentComponent defaultEquipment(Weapon type) {
        Entity weapon = WeaponFactory.createWeapon(type);
        EquipmentComponent equipment = new EquipmentComponent();

        equipment.currentWeapon = weapon;
        equipment.weaponInventory.add(weapon);
        
        return equipment;
    }
    
    public static InventoryComponent inventoryFromData(InventoryData data, final TextureManager TEXTURE) {
        InventoryComponent inventory = new InventoryComponent();

        if (data == null || data.items == null) return inventory;

        ArrayList<Entity> entities = new ArrayList<>();

        for (InventoryData.ItemData itemData : data.items) {
            Entity entity = ObjectFactory.createItemFromName(itemData.name, TEXTURE);
            
            StockComponent stock = entity.getComponent(StockComponent.class);
            if (stock != null) stock.setStock(itemData.stock);

            entities.add(entity);
        }

        inventory.setObjects(entities);

        return inventory;
    }
    
    public static EquipmentComponent equipmentFromData(EquipmentData data) {
        EquipmentComponent equipment = new EquipmentComponent();

        if (data == null) {
            System.out.println("Ha ocurrido un problema al cargar el equipamiento... | ComponentPresets");
            return equipment;
        }

        ArrayList<Entity> weapons = new ArrayList<>();

        // 1. Cargar todas las armas del inventario
        for (String weaponName : data.weaponInventoryNames) {
            Entity weaponEntity = WeaponFactory.createWeaponFromName(weaponName);
            if (weaponEntity != null) {
                weapons.add(weaponEntity);
            } else {
                System.out.println("No se pudo crear arma del inventario: " + weaponName);
            }
        }

        // 2. Intentar asignar el arma actual
        if (data.currentWeaponName != null) {
            Entity currentWeapon = weapons.stream()
                .filter(w -> w.getComponent(NameComponent.class).name.equals(data.currentWeaponName))
                .findFirst()
                .orElse(null);

            if (currentWeapon == null) {
                // No estaba en inventario: creamos el arma y la agregamos
                currentWeapon = WeaponFactory.createWeaponFromName(data.currentWeaponName);
                if (currentWeapon != null) {
                    weapons.add(currentWeapon);
                }
            }

            equipment.setCurrentWeapon(currentWeapon);
        }
        equipment.setWeaponInventory(weapons);


        // 3. Asignar inventario y currentWeapon
        equipment.setWeaponInventory(weapons);

        return equipment;
    }




    
}

