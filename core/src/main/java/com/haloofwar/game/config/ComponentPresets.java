package com.haloofwar.game.config;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.common.enumerators.EnemyType;
import com.haloofwar.common.enumerators.NPCType;
import com.haloofwar.common.enumerators.ObjectType;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.common.enumerators.PowerUpType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.cameras.GameWorldCamera;
import com.haloofwar.engine.entity.AnimatedEntityDescriptor;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.entity.EntityDescriptor;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.interfaces.MovementController;
import com.haloofwar.game.components.AnimationComponent;
import com.haloofwar.game.components.CollectComponent;
import com.haloofwar.game.components.CollisionComponent;
import com.haloofwar.game.components.Component;
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
import com.haloofwar.game.components.RenderComponent;
import com.haloofwar.game.components.ShieldComponent;
import com.haloofwar.game.components.StockComponent;
import com.haloofwar.game.components.TransformComponent;
import com.haloofwar.game.components.VillagerComponent;
import com.haloofwar.game.components.VisibilityComponent;
import com.haloofwar.game.components.WallComponent;
import com.haloofwar.game.factories.WeaponFactory;
import com.haloofwar.interfaces.Talkable;
import com.haloofwar.interfaces.Weapon;

public class ComponentPresets {

	private static final int DEFAULT_HEALTH = 100;
	private static final float WIDTH = 32, HEIGHT = 32;
	private static final float DEFAULT_VELOCITY = 150;
	
	public static TransformComponent defaultTransform(final int identifier, float x, float y) {
		return new TransformComponent(identifier, x, y, WIDTH, HEIGHT);
	}
	
	public static TransformComponent defaultTransform(final int identifier, float x, float y, float width, float height) {
		return new TransformComponent(identifier, x, y, width, height);
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
    
    public static VillagerComponent defaultVillager(NPCType type) {
    	return new VillagerComponent(type);
    }
    
    public static DialogueComponent defualtDialogue(Talkable type, Texture avatar) {
    	return new DialogueComponent(type.getLines(), avatar);
    }
    
    public static PortalComponent defaultPortal(String targetScene) {
		return new PortalComponent(targetScene);
	}
    
    public static EnemyComponent defaultEnemy(EnemyType type) {
		return new EnemyComponent(type);
	}
    
    public static EnemyWeaponAIComponent enemyWeaponAI(float minShootTimer, float maxShootTimer) {
    	return new EnemyWeaponAIComponent(minShootTimer, maxShootTimer);
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
    
    public static WallComponent defaultWall() {
    	return new WallComponent();
    }
}

