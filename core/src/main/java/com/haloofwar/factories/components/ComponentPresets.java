package com.haloofwar.factories.components;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.CollectComponent;
import com.haloofwar.components.CollisionComponent;
import com.haloofwar.components.CrosshairComponent;
import com.haloofwar.components.DialogueComponent;
import com.haloofwar.components.EnemyComponent;
import com.haloofwar.components.EnemyMovementController;
import com.haloofwar.components.EnemyWeaponAIComponent;
import com.haloofwar.components.HealthComponent;
import com.haloofwar.components.InventoryComponent;
import com.haloofwar.components.MovementComponent;
import com.haloofwar.components.NameComponent;
import com.haloofwar.components.ObstacleComponent;
import com.haloofwar.components.PlayerComponent;
import com.haloofwar.components.PlayerMovementController;
import com.haloofwar.components.PortalComponent;
import com.haloofwar.components.PowerUpComponent;
import com.haloofwar.components.PromptComponent;
import com.haloofwar.components.RenderComponent;
import com.haloofwar.components.ShieldComponent;
import com.haloofwar.components.StockComponent;
import com.haloofwar.components.TransformComponent;
import com.haloofwar.components.VillagerComponent;
import com.haloofwar.components.VisibilityComponent;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.enumerators.PlayerType;
import com.haloofwar.enumerators.PowerUpType;
import com.haloofwar.events.EventBus;
import com.haloofwar.interfaces.AnimatedEntityDescriptor;
import com.haloofwar.interfaces.Component;
import com.haloofwar.interfaces.EntityDescriptor;
import com.haloofwar.interfaces.MovementController;
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
    
    // Porque puede ser un arma de fuego o un arma blanca
    public static Component defaultWeapon(Weapon weapon) {
		return weapon.createComponent();
	}
    
    public static ObstacleComponent defaultObstacle() {
    	return new ObstacleComponent();
    }
    
    public static RenderComponent defaultRender(EntityDescriptor type, TextureManager manager) {
    	Texture texture = manager.get(type);
        return new RenderComponent(texture);
    }

    public static StockComponent defaultPickup() {
        return new StockComponent();
    }
    
    public static PlayerComponent defaultPlayer() {
    	return new PlayerComponent();
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
}

